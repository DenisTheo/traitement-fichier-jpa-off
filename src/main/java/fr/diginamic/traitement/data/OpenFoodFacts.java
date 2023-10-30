package fr.diginamic.traitement.data;

import fr.diginamic.traitement.entities.Score;
import fr.diginamic.traitement.models.Additive;
import fr.diginamic.traitement.models.Allergen;
import fr.diginamic.traitement.models.Brand;
import fr.diginamic.traitement.models.Category;
import fr.diginamic.traitement.models.Ingredient;
import fr.diginamic.traitement.models.Product;
import fr.diginamic.traitement.util.Preconditions;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenFoodFacts
{
    private static final String FILE_PATH = "src/main/resources/input/open-food-facts.csv";
    private static final int CATEGORY_INDEX = 0;
    private static final int BRAND_INDEX = 1;
    private static final int PRODUCT_NAME_INDEX = 2;
    private static final int NUTRITIONAL_SCORE_INDEX = 3;
    private static final int INGREDIENTS_INDEX = 4;
    private static final int calories_INDEX = 5;
    private static final int FAT_QUANTITY_INDEX = 6;
    private static final int ALLERGENS_INDEX = 8;
    private static final int ADDITIVES_INDEX = 9;

    private final List<Product> products;
    private final List<String> errors; // Products that couldn't be Fetched

    private OpenFoodFacts(List<Product> products, List<String> errors)
    {
        this.products = products;
        this.errors = errors;
    }

    public List<Product> getProducts()
    {
        return this.products;
    }

    public List<String> getErrors()
    {
        return this.errors;
    }
    
    public static OpenFoodFacts create()
    {
        Path csv = Paths.get(FILE_PATH);
        List<String> errors = new ArrayList<>();
        List<Product> accumulator = new ArrayList<>();
        
        if (Files.exists(csv))
        {// Reads all lines in the file.
            List<String> rows = null;
            
            try
            {
                rows = Files.readAllLines(csv);
            }catch (Exception e)
            {
                throw new RuntimeException(e);
            }
            
            rows.remove(0); // Skipping the Header
            for(String row : rows)
            {
                Product product = buildProduct(row);
                
                if (product != null)
                    accumulator.add(product);
                else
                    errors.add(row);
            }
        }
        
        return new OpenFoodFacts(accumulator, errors);
    }

    private static Product buildProduct(String row)
    {
        String[] columns = row.split("\\|");
        try
        {
            String category = columns[CATEGORY_INDEX];
            if (!Preconditions.isMax(category, 50))
                return null;

            String brand = columns[BRAND_INDEX];
            if (!Preconditions.isMax(brand, 50))
                return null;

            String name = columns[PRODUCT_NAME_INDEX];

            String score = columns[NUTRITIONAL_SCORE_INDEX];

            String calories = columns[calories_INDEX];
            if (!Preconditions.isNumeric(calories))
                calories = "0.0";

            String fat = columns[FAT_QUANTITY_INDEX];
            if (!Preconditions.isNumeric(fat))
                fat = "0.0";

            Set<Ingredient> ingredients = new HashSet<>();
            String[] ingredientElemnts = columns[INGREDIENTS_INDEX].split(",");
            if (ingredientElemnts.length < 2)
                return null;

            for (String ingredientElemnt : ingredientElemnts)
            {
                if (ingredientElemnt.contains(":"))
                    continue;

                String cleanedIngredient = cleanIngredient(ingredientElemnt);
                if (!cleanedIngredient.isBlank() && Preconditions.isMax(cleanedIngredient, 128))
                {
                    Ingredient ingredient = new Ingredient();
                    ingredient.setName(cleanedIngredient);
                    ingredients.add(ingredient);
                }
            }

            if (ingredients.isEmpty())
                return null;

            String[] allergenElements = columns[ALLERGENS_INDEX].split(",");
            Set<Allergen> allergens = new HashSet<>();
            
            for (String allergenElement : allergenElements)
            {
                String cleanedAllergen = cleanAllergen(allergenElement);
                if (!cleanedAllergen.isBlank() && Preconditions.isMax(cleanedAllergen, 50))
                {
                    Allergen allergen = new Allergen();
                    allergen.setName(cleanedAllergen);
                    allergens.add(allergen);
                }
            }
            
            String[] additiveElements = columns[ALLERGENS_INDEX].split(",");
            Set<Additive> additives = new HashSet<>();
            
            for (String additiveElement : additiveElements)
            {
                String cleanedAdditive = cleanAdditive(additiveElement);
                if (!cleanedAdditive.isBlank() && Preconditions.isMax(cleanedAdditive, 50))
                {
                    Additive additive = new Additive();
                    additive.setName(cleanedAdditive);
                    additives.add(additive);
                }
            }

            // Creates the Product Object
            Product product = new Product();
            product.setName(name);
            product.setScore(Score.parse(score));
            product.setCalories(Double.parseDouble(calories));
            product.setFat(Double.parseDouble(fat));
            product.setIngredients(ingredients);
            product.setAllergens(allergens);
            product.setCategory(new Category(category));
            product.setBrand(new Brand(brand));
            product.setAdditives(additives);

            return product;

        } catch (Exception e)
        {
        	e.printStackTrace();
            return null;
        }
    }

    private static String cleanIngredient(String element)
    {
        String result = element.toLowerCase() .replaceAll("_", "").replaceAll("[^a-zA-Z]+$", "").trim();

        if (result.isBlank())
            return "";
        
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    private static String cleanAllergen(String element)
    {
        String result = element.toLowerCase().trim().replaceAll("^[a-zA-Z]{2}:", "").replaceAll("[^a-zA-Z]+$", "");

        if (result.isBlank())
            return "";

        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }

    private static String cleanAdditive(String element)
    {
       //TODO

        return element;
    }
}
