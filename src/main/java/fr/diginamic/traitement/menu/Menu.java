package fr.diginamic.traitement.menu;

import fr.diginamic.traitement.commands.Command;
import fr.diginamic.traitement.commands.ProductsByBrandCommand;
import fr.diginamic.traitement.commands.ProductsByBrandExcludingAllergenCommand;
import fr.diginamic.traitement.commands.ProductsByBrandExcludingIngredientCommand;
import fr.diginamic.traitement.commands.ProductsByCategoryCommand;
import fr.diginamic.traitement.commands.ProductsByCategoryExcludingAllergenCommand;
import fr.diginamic.traitement.commands.ProductsByCategoryExcludingIngredientCommand;
import fr.diginamic.traitement.dao.ProductRepository;
import fr.diginamic.traitement.internal.ServiceLoader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import static fr.diginamic.traitement.util.Preconditions.isInteger;
import static fr.diginamic.traitement.util.Preconditions.withinRange;

public class Menu
{
    private static final Map<Integer, Command> commands = new LinkedHashMap<>();

    private Menu() { }

    public static void displayOptions() throws Exception
    {
        Scanner scanner = new Scanner(System.in);

        bindOptions(ServiceLoader.provideProductRepository(), scanner);

        String input;
        int option = 0;
        do
        {
            System.out.println("\nSelect an Option:");
            input = scanner.nextLine();
            
            try
            {
            	option = Integer.parseInt(input);
            }catch(Exception e) { };
        }while(!(isInteger(input) && withinRange(Integer.parseInt(input), 1, commands.size())));

        var command = commands.get(option);
        if (command != null)
            command.execute();
    }

    private static void bindOptions(ProductRepository repository, Scanner scanner)
    {
        commands.put(1, new ProductsByCategoryCommand(repository, scanner));
        commands.put(2, new ProductsByBrandCommand(repository, scanner));
        commands.put(3, new ProductsByCategoryExcludingIngredientCommand(repository, scanner));
        commands.put(4, new ProductsByBrandExcludingIngredientCommand(repository, scanner));
        commands.put(5, new ProductsByCategoryExcludingAllergenCommand(repository, scanner));
        commands.put(6, new ProductsByBrandExcludingAllergenCommand(repository, scanner));
    }
}
