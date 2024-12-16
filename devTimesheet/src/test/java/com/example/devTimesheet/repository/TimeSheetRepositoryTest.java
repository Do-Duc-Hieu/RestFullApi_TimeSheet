package com.example.devTimesheet.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.devTimesheet.entity.*;

@DataJpaTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("/test.properties")
public class TimeSheetRepositoryTest {

    @Autowired
    private TimeSheetRepository timeSheetRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Branch branch = Branch.builder().nameBranch("hn1").build();

        User user = User.builder().username("testuser").build();

        Project project = Project.builder().nameProject("Test Project").build();

        Status status = Status.builder().nameStatus("In Progress").build();

        TimeSheet timeSheet1 = TimeSheet.builder()
                .date(LocalDate.of(2024, 7, 25))
                .task("Test Task")
                .note("Test Note")
                .workingTime(8)
                .type("Regular")
                .user(user)
                .project(project)
                .status(status)
                .build();

        TimeSheet timeSheet2 = TimeSheet.builder()
                .date(LocalDate.of(2024, 8, 25))
                .task("Test Task")
                .note("Test Note")
                .workingTime(8)
                .type("Regular")
                .user(user)
                .project(project)
                .status(status)
                .build();
    }

    @Test
    void testFindByUsernameAndDate() {
        List<TimeSheet> timeSheets = timeSheetRepository.findByUsernameAndDate("testuser", LocalDate.of(2024, 7, 25));
        assertThat(timeSheets).isNotEmpty();
        assertThat(timeSheets.get(0).getTask()).isEqualTo("Test Task");
        assertThat(timeSheets.get(0).getUser().getUsername()).isEqualTo("testuser");
    }
}
