package fr.diginamic.traitement;

import fr.diginamic.traitement.dao.ProductDao;
import fr.diginamic.traitement.data.OpenFoodFacts;
import fr.diginamic.traitement.models.Product;
import fr.diginamic.traitement.internal.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateDB
{
    public static void main(String[] args)
    {
        OpenFoodFacts foodFacts = OpenFoodFacts.create();
        ProductDao productDao = ServiceLoader.provideProductDao();

        productDao.keepErrors(foodFacts.getErrors());

        Set<Product> products = foodFacts.getProducts().stream().collect(Collectors.toSet());

        productDao.insertAll(products);
    }
}
