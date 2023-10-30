package fr.diginamic.traitement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Category DB Entity
 */
@Entity
@Table(name = "categories")
public class CategoryEntity
{
    /**
     * Category's DB ID
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Category Name
     */
    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    /**
     * Products Tagged with this Category
     */
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products = new ArrayList<>();

    /**
     * Empty Constructor
     */
    public CategoryEntity() { }

    /**
     * Constructor
     * @param name	Category Name
     */
    public CategoryEntity(String name)
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
