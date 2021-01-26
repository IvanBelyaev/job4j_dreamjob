package ru.job4j.dreamjob.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PersonnelOffecerTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 26.01.2021
 */
public class PersonnelOffecerTest {
    /**
     * Simple test for PersonnelOfficer.
     */
    @Test
    public void whenCreatesPersonnelOfficerThenHeHasTheSameParameters() {
        PersonnelOfficer personnelOfficer = new PersonnelOfficer("Ivan", "Ivanov", "Oracle");
        assertThat(personnelOfficer.getFirstName(), is("Ivan"));
        assertThat(personnelOfficer.getLastName(), is("Ivanov"));
        assertThat(personnelOfficer.getCompany(), is("Oracle"));
    }
}
