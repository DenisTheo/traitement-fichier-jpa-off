package fr.diginamic.traitement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Product DB Entity
 */
@Entity
@Table(name = "products")
public class ProductEntity
{
    /**
     * Product's DB ID
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Product Name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * NutriScore
     */
    @Column(name = "score", length = 2)
    @Enumerated(value = EnumType.STRING)
    private Score score;

    /**
     * Calories in 100g
     */
    @Column(name = "calories")
    private double calories;

    /**
     * Fat in 100g
     */
    @Column(name = "fat")
    private double fat;

    /**
     * Product Category
     */
    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity category;

    /**
     * Brand that Produces the Item
     */
    @ManyToOne()
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private BrandEntity brand;

    /**
     * Ingredients in the Product
     */
    @ManyToMany()
    @JoinTable(name = "project_ingredient",
               joinColumns = @JoinColumn(name = "product_id"),
               inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<IngredientEntity> ingredients = new HashSet<>();

    /**
     * Allergens in the Product
     */
    @ManyToMany()
    @JoinTable(name = "project_allergen",
               joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "allergen_id", referencedColumnName = "id"))
    private Set<AllergenEntity> allergens = new HashSet<>();

    /**
     * Additives in the Product
     */
    @ManyToMany()
    @JoinTable(name = "project_additive",
               joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "additive_id", referencedColumnName = "id"))
    private List<AdditiveEntity> additives = new ArrayList<>();

    @Override
    public String toString()
    {
        return "Product " + id + ": \'" + name + "\' " + ", NutriScore " + score + ", " + calories + "Calories and " + fat + " fat for 100g";
    }

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

    public CategoryEntity getCategory()
    {
        return category;
    }

    public void setCategory(CategoryEntity category)
    {
        this.category = category;
    }

    public BrandEntity getBrand()
    {
        return brand;
    }

    public void setBrand(BrandEntity brand)
    {
        this.brand = brand;
    }

    public Set<IngredientEntity> getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(Set<IngredientEntity> ingredients)
    {
        this.ingredients = ingredients;
    }

    public Set<AllergenEntity> getAllergens()
    {
        return allergens;
    }

    public void setAllergens(Set<AllergenEntity> allergens)
    {
        this.allergens = allergens;
    }

    public List<AdditiveEntity> getAdditives()
    {
        return additives;
    }

    public void setAdditives(List<AdditiveEntity> additives)
    {
        this.additives = additives;
    }
}
