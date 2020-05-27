package com.silviucanton.bugreportingsystem.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private BugStatus status;

    @Column(name = "time_reported")
    private LocalDateTime timeReported;

    @ManyToOne
    @JoinColumn(name = "tester")
    private QATester reporter;

    public Bug(int id, String name, String description, LocalDateTime timeReported) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timeReported = timeReported;
        this.status = BugStatus.NOT_TRACKED;
    }

    public Bug(String name, String description, LocalDateTime timeReported) {
        this(0, name, description, timeReported);
    }

    public Bug() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeReported() {
        return timeReported;
    }

    public void setTimeReported(LocalDateTime timeReported) {
        this.timeReported = timeReported;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public QATester getReporter() {
        return reporter;
    }

    public void setReporter(QATester reporter) {
        this.reporter = reporter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return id == bug.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Bug{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", timeReported=" + timeReported +
                '}';
    }
}
