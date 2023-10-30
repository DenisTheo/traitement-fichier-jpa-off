package fr.diginamic.traitement.dao;

import fr.diginamic.traitement.entities.ProductEntity;
import fr.diginamic.traitement.models.Product;
import java.util.function.Function;

public interface ProductMapper extends Function<ProductEntity, Product> { }
