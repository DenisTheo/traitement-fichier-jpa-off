package fr.diginamic.traitement.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ingredient DB Entity
 */
@Entity
@Table(name = "ingredients")
public class IngredientEntity
{
    /**
     * Ingredient DB Entity
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Ingredient Name
     */
    @Column(name = "name", unique = true, length = 128, nullable = false)
    private String name;

    /**
     * Products tagged with this Ingredient
     */
    @ManyToMany(mappedBy = "ingredients")
    private List<ProductEntity> products = new ArrayList<>();
    ;

    /**
     * Empty Constructor
     */
    public IngredientEntity() { }

    /**
     * Constructor
     *
     * @param name	Ingredient Name
     */
    public IngredientEntity(String name)
    {
        this.name = name;
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

    public List<ProductEntity> getProducts()
    {
        return products;
    }

    public void setProducts(List<ProductEntity> products)
    {
        this.products = products;
    }
}
