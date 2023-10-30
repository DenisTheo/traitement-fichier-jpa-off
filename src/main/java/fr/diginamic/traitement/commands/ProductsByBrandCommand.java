package fr.diginamic.traitement.commands;

import fr.diginamic.traitement.menu.EmptyInputException;
import fr.diginamic.traitement.dao.ProductRepository;
import fr.diginamic.traitement.models.Product;
import java.util.List;
import java.util.Scanner;

public class ProductsByBrandCommand implements Command
{
    private final ProductRepository repository;
    private final Scanner scanner;

    public ProductsByBrandCommand(ProductRepository repository, Scanner scanner)
    {
        System.out.println("2. Display the 10 Highest-Rated Products from a Brand");
        this.repository = repository;
        this.scanner = scanner;
    }

    @Override
    public void execute() throws Exception
    {
        System.out.println("Enter Brand Name:");
        String brand = scanner.nextLine();
        
        if (!brand.isBlank())
            execute(brand, repository);
        else
            throw new EmptyInputException();
    }

    private void execute(String brand, ProductRepository repository)
    {
        List<Product> products = repository.getProductsByBrand(brand, null, null, 10);
        
        int count = 1;
        for(Product product : products)
        {
            System.out.printf("%d: %s%n", count, product.getName());
            count++;
        }
    }
}
