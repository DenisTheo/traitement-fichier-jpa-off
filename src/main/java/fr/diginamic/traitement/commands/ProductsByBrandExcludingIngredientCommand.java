package fr.diginamic.traitement.commands;

import fr.diginamic.traitement.menu.EmptyInputException;
import fr.diginamic.traitement.dao.ProductRepository;
import fr.diginamic.traitement.models.Product;

import java.util.List;
import java.util.Scanner;

public class ProductsByBrandExcludingIngredientCommand implements Command
{
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByBrandExcludingIngredientCommand(ProductRepository repository, Scanner scanner)
    {
        System.out.println("4. Display the 10 Highest-Rated Products from a Brand (Excluding a given Ingredient)");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception
    {
        System.out.println("Enter a Brand Name:");
        String brand = scanner.nextLine();
        
        if (brand.isBlank())
            throw new EmptyInputException();

        System.out.println("Enter the Ingredient to Exclude:");
        String ingredient = scanner.nextLine();
        
        if (ingredient.isBlank())
            throw new EmptyInputException();

        execute(brand, ingredient, repository);
    }

    private void execute(String brand, String ingredient, ProductRepository repository)
    {
        List<Product> products = repository.getProductsByBrand(brand, null, ingredient, 10);
        
        int count = 1;
        for(Product product : products)
        {
            System.out.printf("%d: %s%n", count, product.getName());
            count++;
        }
    }
}
