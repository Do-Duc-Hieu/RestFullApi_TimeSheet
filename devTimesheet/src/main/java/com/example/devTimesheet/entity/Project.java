    package com.example.devTimesheet.entity;
    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Table(name = "project")
    public class Project {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;
        String nameProject;

        LocalDate date;
        String note;

        @ManyToOne
        @JoinColumn(name = "client_id")
        Client client;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "project",
                cascade = {CascadeType.DETACH, CascadeType.MERGE
                        , CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
        List<TimeSheet> timeSheets;

        @ManyToOne
        @JoinColumn(name = "team_id")
        Team team;

        @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE
                , CascadeType.PERSIST, CascadeType.REFRESH})
        @JoinTable(name = "project_task",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "task_id"))
        @JsonManagedReference
        List<Task> tasks;

    }
