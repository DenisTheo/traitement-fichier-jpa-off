package fr.diginamic.traitement.dao;

public interface Dao<T, E>
{
    E insert(T type);
}
