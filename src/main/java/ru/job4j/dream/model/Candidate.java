package ru.job4j.dream.model;

import java.util.Objects;

/**
 * Candidate.
 */
public class Candidate {
    /** ID. */
    private int id;
    /** Candidate name. */
    private String name;

    /**
     * Constructor.
     * @param id ID.
     * @param name candidate name.
     */
    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets ID.
     * @return ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets new ID.
     * @param id new ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets candidate name.
     * @return candidate name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new candidate name.
     * @param name new candidate name.
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
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                Objects.equals(name, candidate.name);
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
