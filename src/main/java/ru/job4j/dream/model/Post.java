package ru.job4j.dream.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Post.
 * Describes a vacancy.
 */
public class Post {
    /** ID. */
    private int id;
    /** Job Title. */
    private String name;
    /** Vacancy description. */
    private String description;
    /** Job posting date. */
    private final LocalDateTime created;

    /**
     * Constructor.
     * @param id ID.
     * @param name job title.
     * @param description vacancy description.
     */
    public Post(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
    }

    /**
     * Gets id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new id.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns vacancy name.
     * @return vacancy name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new vacancy description.
     * @param description new vacancy description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets vacancy description.
     * @return vacancy description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets job posting date.
     * @return job posting date.
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Sets new vacancy name.
     * @param name new vacancy name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param o other object.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id;
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

