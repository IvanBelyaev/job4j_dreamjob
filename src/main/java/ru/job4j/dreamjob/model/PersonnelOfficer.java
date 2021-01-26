package ru.job4j.dreamjob.model;

/**
 * Personnel Officer.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 26.01.2021
 */
public class PersonnelOfficer {
    /** First name. */
    private final String firstName;
    /** Last name. */
    private final String lastName;
    /** Company. */
    private final String company;

    /**
     * Constructor.
     * @param firstName first name.
     * @param lastName last name.
     * @param company company.
     */
    public PersonnelOfficer(String firstName, String lastName, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
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
     * Gets company.
     * @return company.
     */
    public String getCompany() {
        return company;
    }
}
