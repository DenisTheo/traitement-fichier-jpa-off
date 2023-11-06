package fr.diginamic.traitement.models;

public class Brand
{
    private long id;
    private String name;

    public Brand(String name)
    {
        setName(name);
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
}
