package fr.diginamic.traitement.internal;

import fr.diginamic.traitement.dao.AllergenDao;
import fr.diginamic.traitement.dao.BrandDao;
import fr.diginamic.traitement.dao.CategoryDao;
import fr.diginamic.traitement.dao.IngredientDao;
import fr.diginamic.traitement.dao.ProductDao;
import fr.diginamic.traitement.entities.BrandEntity;
import fr.diginamic.traitement.entities.CategoryEntity;
import fr.diginamic.traitement.entities.FailedProductEntity;
import fr.diginamic.traitement.entities.ProductEntity;
import fr.diginamic.traitement.models.Allergen;
import fr.diginamic.traitement.models.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

class ProductDaoImpl implements ProductDao
{
    @Override
    public void insertAll(Iterable<Product> products)
    {
        EntityManager manager = ServiceLoader.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        BrandDao brandDao = ServiceLoader.provideBrandDao();
        CategoryDao categoryDao = ServiceLoader.provideCategoryDao();
        IngredientDao ingredientDao = ServiceLoader.provideIngredientDao();
        AllergenDao allergenDao = ServiceLoader.provideAllergenDao();

        for(Product product : products)
        {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(product.getName());
            productEntity.setScore(product.getScore());
            productEntity.setCalories(product.getCalories());
            productEntity.setFat(product.getFat());

            Set<String> names = product.getAllergens().stream().map(Allergen::getName).collect(Collectors.toSet());

            ExecutorService executor = Executors.newFixedThreadPool(20);
            List<Future<?>> futures = List.of(executor.submit(() -> { BrandEntity brandEntity = brandDao.insert(product.getBrand());
                        											productEntity.setBrand(brandEntity);}),
            								  executor.submit(() -> { CategoryEntity categoryEntity = categoryDao.insert(product.getCategory());
            														productEntity.setCategory(categoryEntity);}),
            								  executor.submit(() -> {ingredientDao.insertAll(product.getIngredients());}),
            								  executor.submit(() -> {allergenDao.insertAll(product.getAllergens());}));
            
            futures.forEach(future ->
            {
                try
                {
                    future.get();
                }catch(InterruptedException | ExecutionException e)
                {
                	throw new RuntimeException(e);
                }
            });

            executor.shutdown();

            productEntity.setIngredients(ingredientDao.findByName(names, manager));
            productEntity.setAllergens(allergenDao.findByName(names, manager));
            manager.persist(productEntity);
        }
        
        transaction.commit();
    }

    @Override
    public void keepErrors(Iterable<String> errors)
    {
        EntityManager manager = ServiceLoader.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        for(String error : errors)
        {
            FailedProductEntity failedProduct = new FailedProductEntity();
            failedProduct.setEntry(error);
            manager.persist(failedProduct);
        }
        
        transaction.commit();
    }
}
