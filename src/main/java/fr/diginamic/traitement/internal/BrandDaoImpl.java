package fr.diginamic.traitement.internal;

import fr.diginamic.traitement.dao.BrandDao;
import fr.diginamic.traitement.entities.BrandEntity;
import fr.diginamic.traitement.models.Brand;
import javax.persistence.EntityManager;

class BrandDaoImpl implements BrandDao
{
    @Override
    public BrandEntity insert(Brand brand)
    {
        EntityManager manager = ServiceLoader.createEntityManager();
        manager.getTransaction().begin();

        BrandEntity entity = findByName(brand.getName(), manager);

        if (entity == null)
            try
        	{
                entity = new BrandEntity();
                entity.setName(brand.getName());
                manager.persist(entity);
            }catch(Exception e)
        	{
                throw new RuntimeException(e);
            }

        manager.getTransaction().commit();
        
        return entity;
    }

    private BrandEntity findByName(String name, EntityManager manager)
    {
        return manager.createQuery("SELECT b FROM BrandEntity b WHERE b.name=:name", BrandEntity.class).setParameter("name", name).setMaxResults(1).getResultStream().findFirst().orElse(null);
    }
}
