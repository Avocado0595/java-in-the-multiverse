package org.example.model;

import java.time.LocalDate;

public class RegistedCourse extends Course{
    public RegistedCourse(int id, String name, String description, LocalDate enrollDate) {
        super(id, name, description);
        this.enrollDate = enrollDate;
    }
    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    private LocalDate enrollDate;

}
