package fr.diginamic.traitement.dao;

import javax.persistence.EntityManager;
import java.util.Set;

/**
 * A generic interface to simplify operations on a Collection of Objects designed for the Application
 *
 * @param <T> Input
 * @param <E> Output
 */
public interface IterableDao<T, E>
{
    /**
     * Inserts Entities into the DB
     *
     * @param types		List of Entries to be inserted
     */
    void insertAll(Iterable<T> types);

    /**
     * Inserts entities into the DB using an EntityManager
     *
     * @param types		List of Entries to be inserted
     * @param manager	EntityManager to be used
     */
    void insertAll(Iterable<T> types, EntityManager manager);

    /**
     * Gets Entities with given Name
     *
     * @param names		Collection of Names to Fetch
     * @return Entities of Name within names
     */
    Set<E> findByName(Iterable<String> names);

    /**
     * Gets entities based on the given Names using an EntityManager
     *
     * @param names		Collection of Names to Fetch
     * @param manager	EntityManager to be used
     * @return the entities that match the provided names
     */
    Set<E> findByName(Iterable<String> names, EntityManager manager);
}
