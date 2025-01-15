package com.medilabo.patientservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
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
    @Size(min = 1)
    @Column(name = "patient_naissance")
    private String dateNaissance;

    @NotNull
    @Size(min = 1)
    @Column(name = "patient_genre")
    private String genre;

    @Column(name = "patient_addresse")
    private String adressePostale;

    @Column(name = "patient_telephone")
    private String numeroTelephone;

    public Patient() {
    }

    public Patient(String prenom, String nom, String dateNaissance, String genre, String adressePostale,
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

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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
