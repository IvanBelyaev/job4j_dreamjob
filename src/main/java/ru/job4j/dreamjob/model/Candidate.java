package ru.job4j.dreamjob.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Candidate.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 26.01.2021
 */
public class Candidate {
    /** First name. */
    private final String firstName;
    /** Last name. */
    private final String lastName;
    /** Date of birth. */
    private final LocalDate dateOfBirth;
    /** Work experience. */
    private String workExperience;

    /**
     * Constructor.
     * @param firstName first name.
     * @param lastName last name.
     * @param dateOfBirth date of birth.
     * @param workExperience work experience.
     */
    public Candidate(String firstName, String lastName, String dateOfBirth, String workExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        this.dateOfBirth = LocalDate.from(dateTimeFormatter.parse(dateOfBirth));
        this.workExperience = workExperience;
    }

    /**
     * Gets first name.
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name.
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets date of birth.
     * @return date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Gets work experience.
     * @return work experience.
     */
    public String getWorkExperience() {
        return workExperience;
    }

    /**
     * Sets work experience.
     * @param workExperience new work experience.
     */
    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}
