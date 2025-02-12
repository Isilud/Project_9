package com.medilabo.diagnosticservice.model;

import org.springframework.aot.generate.Generated;

@Generated
public class Note {

    private String text;

    public Note(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
