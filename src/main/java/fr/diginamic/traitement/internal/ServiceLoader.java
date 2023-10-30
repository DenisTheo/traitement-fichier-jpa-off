package fr.diginamic.traitement.internal;

import fr.diginamic.traitement.dao.AllergenDao;
import fr.diginamic.traitement.dao.BrandDao;
import fr.diginamic.traitement.dao.CategoryDao;
import fr.diginamic.traitement.dao.IngredientDao;
import fr.diginamic.traitement.dao.ProductDao;
import fr.diginamic.traitement.dao.ProductMapper;
import fr.diginamic.traitement.dao.ProductRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceLoader
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("food");
    private static final ProductMapper productMapper = new ProductMapperImpl();
    private static final BrandDao brandDao = new BrandDaoImpl();
    private static final ProductDaoImpl productDao = new ProductDaoImpl();
    private static final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    private static final IngredientDaoImpl ingredientDao = new IngredientDaoImpl();
    private static final AllergenDao allergenDao = new AllergenDaoImpl();
    private static final ProductRepository productRepository = new ProductRepositoryImpl();

    public static EntityManager createEntityManager()
    {
        // Construct an entity manager for handling database transactions
        return emf.createEntityManager();
    }

    public static BrandDao provideBrandDao()
    {
        return brandDao;
    }

    public static ProductDao provideProductDao()
    {
        return productDao;
    }

    public static CategoryDao provideCategoryDao()
    {
        return categoryDao;
    }

    public static IngredientDao provideIngredientDao()
    {
        return ingredientDao;
    }

    public static ProductMapper provideProductMapper()
    {
        return productMapper;
    }

    public static AllergenDao provideAllergenDao()
    {
        return allergenDao;
    }

    public static ProductRepository provideProductRepository()
    {
        return productRepository;
    }
}
