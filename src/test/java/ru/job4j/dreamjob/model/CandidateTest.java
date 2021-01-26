package ru.job4j.dreamjob.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * CandidateTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 26.01.2021
 */
public class CandidateTest {
    /**
     * First simple test for Candidate.
     */
    @Test
    public void whenCreatesCandidateThenHeHasTheSameParameters() {
        Candidate candidate = new Candidate(
                "Ivan", "Ivanov", "01 02 2003", "2 years"
        );
        assertThat(candidate.getFirstName(), is("Ivan"));
        assertThat(candidate.getLastName(), is("Ivanov"));
        assertThat(candidate.getWorkExperience(), is("2 years"));
        assertThat(candidate.getDateOfBirth().getYear(), is(2003));
        assertThat(candidate.getDateOfBirth().getMonthValue(), is(2));
        assertThat(candidate.getDateOfBirth().getDayOfMonth(), is(1));
    }

    /**
     * Second simple test for Candidate.
     */
    @Test
    public void whenSetNewWorkExperienceThenCandidateHasTheSame() {
        Candidate candidate = new Candidate(
                "Ivan", "Ivanov", "01 02 2003", "2 years"
        );
        candidate.setWorkExperience("3 years");
        assertThat(candidate.getWorkExperience(), is("3 years"));
    }
}
