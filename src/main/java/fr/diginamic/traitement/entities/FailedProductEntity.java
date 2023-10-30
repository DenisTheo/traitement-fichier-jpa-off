package fr.diginamic.traitement.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Representing a DB Request Error
 */
@Entity
@Table(name = "errors")
public class FailedProductEntity
{
    /**
     * Product's DB ID
     */
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Error String
     */
    @Column(name = "entry", nullable = false, columnDefinition = "TEXT")
    private String entry;

    public long getId()
    {
        return id;
    }
    
    public void setId(long id)
    {
        this.id = id;
    }

    public String getEntry()
    {
        return entry;
    }

    public void setEntry(String entry)
    {
        this.entry = entry;
    }
}
