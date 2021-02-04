package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Store.
 * Simple store. Singleton.
 */
public class Store {
    /** Single instance of the class. */
    private static final Store INST = new Store();
    /** Vacancies. */
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    /** Candidates. */
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    /** ID counter for posts. */
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    /** ID counter for candidates. */
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);

    /**
     * Constructor.
     */
    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "no work experience required"));
        posts.put(2, new Post(2, "Middle Java Job", "Experience from 2 years"));
        posts.put(3, new Post(3, "Senior Java Job", "Experience from 5 years"));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    /**
     * Gets a link to a singleton
     * @return a ling to a singleton.
     */
    public static Store instOf() {
        return INST;
    }

    /**
     * Saves a vacancy.
     * @param post new vacancy.
     */
    public void save(Post post) {
        post.setId(POST_ID.incrementAndGet());
        posts.put(post.getId(), post);
    }

    /**
     * Saves a new candidate.
     * @param candidate new candidate.
     */
    public void save(Candidate candidate) {
        candidate.setId(CANDIDATE_ID.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }

    /**
     * Gets all vacancies.
     * @return all vacancies.
     */
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    /**
     * Gets all candidates.
     * @return all candidates.
     */
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }
}
