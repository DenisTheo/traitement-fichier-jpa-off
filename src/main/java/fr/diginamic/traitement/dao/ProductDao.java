package fr.diginamic.traitement.dao;

import fr.diginamic.traitement.models.Product;

public interface ProductDao
{
    /**
     * Inserts a collection of products into the database
     *
     * @param products	Products to be inserted
     */
    void insertAll(Iterable<Product> products);

    void keepErrors(Iterable<String> errors);
}
