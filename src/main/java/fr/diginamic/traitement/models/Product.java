package fr.diginamic.traitement.models;

import fr.diginamic.traitement.entities.Score;
import java.util.Set;

public class Product
{
    private long id;
    private String name;
    private double calories;
    private double fat;
    private Score score;
    private Category category;
    private Brand brand;
    private Set<Ingredient> ingredients;
    private Set<Allergen> allergens;
    private Set<Additive> additives;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Score getScore()
    {
        return score;
    }

    public void setScore(Score score)
    {
        this.score = score;
    }

    public double getCalories()
    {
        return calories;
    }

    public void setCalories(double calories)
    {
        this.calories = calories;
    }
    
    public double getFat()
    {
        return fat;
    }

    public void setFat(double fat)
    {
        this.fat = fat;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Brand getBrand()
    {
        return brand;
    }

    public void setBrand(Brand brand)
    {
        this.brand = brand;
    }

    public Set<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    public Set<Allergen> getAllergens()
    {
        return allergens;
    }

    public void setAllergens(Set<Allergen> allergens)
    {
        this.allergens = allergens;
    }
    
    public Set<Additive> getAdditives()
    {
        return additives;
    }
    
    public void setAdditives(Set<Additive> additives)
    {
        this.additives = additives;
    }
}
