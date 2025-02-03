package com.medilabo.noteservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private Integer patientId;
    private String text;

    public Note(Integer patientId, String text) {
        this.patientId = patientId;
        this.text = text;
    }

    public Note(Note note) {
        this.patientId = note.patientId;
        this.text = note.text;
    }

    public Note() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
