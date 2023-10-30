package fr.diginamic.traitement.internal;

import fr.diginamic.traitement.dao.ProductMapper;
import fr.diginamic.traitement.entities.ProductEntity;
import fr.diginamic.traitement.models.Product;

class ProductMapperImpl implements ProductMapper
{
    @Override
    public Product apply(ProductEntity entity)
    {
        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setScore(entity.getScore());

        return product;
    }
}
