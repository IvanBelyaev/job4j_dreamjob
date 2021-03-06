package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Store.
 * Simple store. Singleton.
 */
public class MemStore implements Store {
    /** Single instance of the class. */
    private static final MemStore INST = new MemStore();
    /** Vacancies. */
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    /** Candidates. */
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    /** Users. */
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final List<String> cities = new CopyOnWriteArrayList<>();
    /** ID counter for posts. */
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    /** ID counter for candidates. */
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    /** ID counter for users. */
    private static AtomicInteger USER_ID = new AtomicInteger(4);

    /**
     * Constructor.
     */
    private MemStore() {
        LocalDateTime now = LocalDateTime.now();
        posts.put(1, new Post(1, "Junior Java Job", "no work experience required", now));
        posts.put(2, new Post(2, "Middle Java Job", "Experience from 2 years", now));
        posts.put(3, new Post(3, "Senior Java Job", "Experience from 5 years", now));
        candidates.put(1, new Candidate(1, "Junior Java", 0));
        candidates.put(2, new Candidate(2, "Middle Java", 1));
        candidates.put(3, new Candidate(3, "Senior Java", 2));
        cities.add("Москва");
        cities.add("Санкт-Петербург");
        cities.add("Самара");
    }

    /**
     * Gets a link to a singleton
     * @return a ling to a singleton.
     */
    public static MemStore instOf() {
        return INST;
    }

    /**
     * Saves a vacancy.
     * @param post new vacancy.
     */
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    /**
     * Saves a new candidate.
     * @param candidate new candidate.
     */
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
    }

    /**
     * Finds a vacancy by ID.
     * @param id vacancy ID.
     * @return found job or null if nothing was found.
     */
    public Post findPostById(int id) {
        return posts.get(id);
    }

    /**
     * Finds a candidate by ID.
     * @param id candidate ID.
     * @return found candidate or null if nothing was found.
     */
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
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

    /**
     * Deletes candidate.
     * @param id candidate ID.
     */
    @Override
    public void deleteCandidate(int id) {
        candidates.remove(id);
    }

    @Override
    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public User findUserByEmail(String email) {
        return users.get(email);
    }

    @Override
    public Map<Integer, String> findAllCities() {
        Map<Integer, String> result = new HashMap<>();
        for (int i = 0; i < cities.size(); i++) {
            result.put(i, cities.get(i));
        }
        return result;
    }

    @Override
    public String findCityById(int id) {
        return cities.get(id);
    }
}
