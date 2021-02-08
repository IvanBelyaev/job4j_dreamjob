package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.Collection;

/**
 * Store.
 * Data warehouse interface.
 */
public interface Store {
    /**
     * Saves a vacancy.
     * @param post new vacancy.
     */
    void save(Post post);

    /**
     * Saves a new candidate.
     * @param candidate new candidate.
     */
    void save(Candidate candidate);

    /**
     * Finds a vacancy by ID.
     * @param id vacancy ID.
     * @return found job or null if nothing was found.
     */
    Post findPostById(int id);

    /**
     * Finds a candidate by ID.
     * @param id candidate ID.
     * @return found candidate or null if nothing was found.
     */
    Candidate findCandidateById(int id);

    /**
     * Gets all vacancies.
     * @return all vacancies.
     */
    Collection<Post> findAllPosts();

    /**
     * Gets all candidates.
     * @return all candidates.
     */
    Collection<Candidate> findAllCandidates();
}
