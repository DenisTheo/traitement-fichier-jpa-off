package fr.diginamic.traitement.commands;

import fr.diginamic.traitement.menu.EmptyInputException;
import fr.diginamic.traitement.dao.ProductRepository;
import fr.diginamic.traitement.models.Product;

import java.util.List;
import java.util.Scanner;

public class ProductsByCategoryCommand implements Command
{
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByCategoryCommand(ProductRepository repository, Scanner scanner)
    {
        System.out.println("1. Display the 10 Highest-Rated Products in a given Category");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception
    {
        System.out.println("Enter a Product Category:");
        String category = scanner.nextLine();
        
        if (!category.isBlank())
            execute(category, repository);
        else
            throw new EmptyInputException();
    }

    private void execute(String category, ProductRepository repository)
    {
        List<Product> products = repository.getProductsByCategory(category, null, null, 10);
        
        int count = 1;
        for(Product product : products)
        {
            System.out.printf("%d: %s%n", count, product.getName());
            count++;
        }
    }
}
