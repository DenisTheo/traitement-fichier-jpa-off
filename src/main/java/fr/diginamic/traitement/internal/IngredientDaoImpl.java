package fr.diginamic.traitement.internal;

import fr.diginamic.traitement.dao.IngredientDao;
import fr.diginamic.traitement.entities.IngredientEntity;
import fr.diginamic.traitement.models.Ingredient;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Set;
import java.util.stream.Collectors;

class IngredientDaoImpl implements IngredientDao
{

    @Override
    public void insertAll(Iterable<Ingredient> ingredients)
    {
        insert(ingredients, ServiceLoader.createEntityManager());
    }

    @Override
    public void insertAll(Iterable<Ingredient> ingredients, EntityManager manager)
    {
        insert(ingredients, manager);
    }

    @Override
    public Set<IngredientEntity> findByName(Iterable<String> names)
    {
        return find(names, ServiceLoader.createEntityManager());
    }

    @Override
    public Set<IngredientEntity> findByName(Iterable<String> names, EntityManager manager)
    {
        return find(names, manager);
    }

    private void insert(Iterable<Ingredient> ingredients, EntityManager manager)
    {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        
        for(Ingredient ingredient : ingredients)
        {
            Query query = manager.createNativeQuery("INSERT IGNORE INTO ingredients (name) VALUES (?)");
            query.setParameter(1, ingredient.getName());
            query.executeUpdate();
        }
        
        transaction.commit();
    }

    private Set<IngredientEntity> find(Iterable<String> names, EntityManager manager)
    {
        return manager.createQuery("SELECT i FROM IngredientEntity i WHERE i.name IN :names", IngredientEntity.class).setParameter("names", names).getResultStream().collect(Collectors.toSet());
    }
}
