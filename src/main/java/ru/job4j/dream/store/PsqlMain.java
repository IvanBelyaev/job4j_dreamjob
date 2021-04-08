package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;

/**
 * PsqlMain.
 * Tests for PsqlStore.
 */
public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job", "Java Job Description", LocalDateTime.now()));
        int id = -1;
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription() + " " + post.getCreated());
            id = post.getId();
        }
        System.out.println(id);
        Post post = store.findPostById(id);
        System.out.println(post.getId() + " " + post.getName() + " " + post.getDescription() + " " + post.getCreated());

        store.save(new Candidate(0, "Java candidate", 1));
        id = -1;
        for (Candidate can : store.findAllCandidates()) {
            System.out.println(can.getId() + " " + can.getName());
            id = can.getId();
        }
        System.out.println(id);
        Candidate can = store.findCandidateById(id);
        System.out.println(can.getId() + " " + can.getName());
    }
}
