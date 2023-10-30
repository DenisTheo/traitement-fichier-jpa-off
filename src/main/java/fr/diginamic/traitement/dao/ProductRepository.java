package fr.diginamic.traitement.dao;

import fr.diginamic.traitement.models.Product;
import java.util.List;

public interface ProductRepository
{
    /**
     * Gets a list of Products from a Category, excluding specific Allergens and Ingredients
     *
     * @param category            The Category of the Products
     * @param allergenToExclude   Allergen to be Excluded from the results
     * @param ingredientToExclude Ingredient to be excluded from the results
     * @param limit               Number of products to fetch
     * @return The limit Number of Products of category Category without allergenToExclude Allergen and ingredientToExclude ingredient.
     */
    List<Product> getProductsByCategory(String category, String allergenToExclude, String ingredientToExclude, int limit);

    /**
     * Gets a list of Products from a Brand, excluding specific Allergens and Ingredients
     *
     * @param brand               The Brand of the Products
     * @param allergenToExclude   Allergen to be Excluded from the results
     * @param ingredientToExclude Ingredient to be excluded from the results
     * @param limit               Number of products to fetch
     * @return The limit Number of Products of brand Brand without allergenToExclude Allergen and ingredientToExclude ingredient.
     */
    List<Product> getProductsByBrand(String brand, String allergenToExclude, String ingredientToExclude, int limit);
}
