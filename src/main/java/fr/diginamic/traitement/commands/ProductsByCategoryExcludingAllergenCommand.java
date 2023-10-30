package fr.diginamic.traitement.commands;

import fr.diginamic.traitement.menu.EmptyInputException;
import fr.diginamic.traitement.dao.ProductRepository;
import fr.diginamic.traitement.models.Product;

import java.util.List;
import java.util.Scanner;

public class ProductsByCategoryExcludingAllergenCommand implements Command
{
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByCategoryExcludingAllergenCommand(ProductRepository repository, Scanner scanner)
    {
        System.out.println("5. Display the 10 highest-Rated Products in a Category (Excluding a given Allergen)");
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

        System.out.println("Enter the Allergen to Exclude:");
        String allergen = scanner.nextLine();
        
        if (allergen.isBlank())
            throw new EmptyInputException();

        execute(category, allergen, repository);
    }

    private void execute(String category, String allergen, ProductRepository repository)
    {
        List<Product> products = repository.getProductsByCategory(category, allergen, null, 10);
        
        int count = 1;
        for(Product product : products)
        {
            System.out.printf("%d: %s%n", count, product.getName());
            count++;
        }
    }
}
