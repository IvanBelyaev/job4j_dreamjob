package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Store.
 * Simple store. Singleton.
 */
public class Store {
    /** Single instance of the class. */
    private static final Store INST = new Store();
    /** Storage. */
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();

    /**
     * Constructor.
     */
    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "no work experience required"));
        posts.put(2, new Post(2, "Middle Java Job", "Experience from 2 years"));
        posts.put(3, new Post(3, "Senior Java Job", "Experience from 5 years"));
    }

    /**
     * Gets a link to a singleton
     * @return a ling to a singleton.
     */
    public static Store instOf() {
        return INST;
    }

    /**
     * Gets all vacancies.
     * @return all vacancies.
     */
    public Collection<Post> findAll() {
        return posts.values();
    }
}
