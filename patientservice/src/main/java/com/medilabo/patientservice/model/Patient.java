package com.medilabo.patientservice.model;

import org.springframework.aot.generate.Generated;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
@Generated
public class Patient {

    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1)
    @Column(name = "patient_prenom")
    private String prenom;

    @NotNull
    @Size(min = 1)
    @Column(name = "patient_nom")
    private String nom;

    @NotNull
    @Column(name = "patient_naissance")
    private LocalDate dateNaissance;

    @NotNull
    @Column(name = "patient_genre")
    private char genre;

    @Column(name = "patient_adresse")
    private String adressePostale;

    @Column(name = "patient_telephone")
    private String numeroTelephone;

    public Patient() {
    }

    public Patient(String prenom, String nom, LocalDate dateNaissance, char genre, String adressePostale,
            String numeroTelephone) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.adressePostale = adressePostale;
        this.numeroTelephone = numeroTelephone;
    }

    public Patient(Patient p) {
        this.prenom = p.prenom;
        this.nom = p.nom;
        this.dateNaissance = p.dateNaissance;
        this.genre = p.genre;
        this.adressePostale = p.adressePostale;
        this.numeroTelephone = p.numeroTelephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}