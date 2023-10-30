package fr.diginamic.traitement.commands;

import fr.diginamic.traitement.menu.EmptyInputException;
import fr.diginamic.traitement.dao.ProductRepository;
import fr.diginamic.traitement.models.Product;
import java.util.List;
import java.util.Scanner;

public class ProductsByCategoryExcludingIngredientCommand implements Command
{
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByCategoryExcludingIngredientCommand(ProductRepository repository, Scanner scanner)
    {
        System.out.println("3. Display the 10 Highest-Rated Products from a given Category (Excludes a given Ingredient)");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception
    {
        System.out.println("Enter a Product Category:");
        String category = scanner.nextLine();
        
        if (category.isBlank())
            throw new EmptyInputException();

        System.out.println("Enter the Ingredient to Exclude:");
        String ingredient = scanner.nextLine();
        
        if (ingredient.isBlank())
            throw new EmptyInputException();

        execute(category, ingredient, repository);
    }

    private void execute(String category, String ingredient, ProductRepository repository)
    {
        List<Product> products = repository.getProductsByCategory(category, null, ingredient, 10);
        
        int count = 1;
        for(Product product : products)
        {
            System.out.printf("%d. %s%n", count, product.getName());
            count++;
        }
    }
}
