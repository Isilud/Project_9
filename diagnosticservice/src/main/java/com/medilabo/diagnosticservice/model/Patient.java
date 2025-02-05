package com.medilabo.diagnosticservice.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.aot.generate.Generated;

@Generated
public class Patient {

    private LocalDate dateNaissance;

    private char genre;

    public Patient() {
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public char getGenre() {
        return genre;
    }

    public void setGenre(char genre) {
        this.genre = genre;
    }

    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
}
