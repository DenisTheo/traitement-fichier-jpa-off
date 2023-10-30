package fr.diginamic.traitement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Allergen DB Entity
 */
@Entity
@Table(name = "allergens")
public class AllergenEntity
{
    /**
     * Allergen DB ID
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Allergen Name
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    /**
     * Products tagged with this Allergen
     */
    @ManyToMany(mappedBy = "allergens")
    private List<ProductEntity> products = new ArrayList<>();
    ;

    /**
     * Empty Constructor
     */
    public AllergenEntity() { }

    /**
     * Constructor
     *
     * @param name	Allergen Name
     */
    public AllergenEntity(String name)
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
