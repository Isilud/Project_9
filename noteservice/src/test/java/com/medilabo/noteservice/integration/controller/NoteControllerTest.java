package com.medilabo.noteservice.integration.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.repository.NoteRepository;

@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NoteControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private NoteRepository noteRepository;

        public Note note1;
        public Note note2;
        public Note note3;

        @BeforeEach
        public void setup() {
                noteRepository.deleteAll();
                note1 = noteRepository.save(new Note(0, "defaultText"));
                note2 = noteRepository.save(new Note(0, "defaultText2"));
                note3 = noteRepository.save(new Note(1, "defaultText3"));
                System.out.println(noteRepository.findAll());
        }

        @Test
        public void testAddNote() throws Exception {
                Note note = new Note(1, "newNoteText");
                mockMvc.perform(post("/notes")
                                .content(objectMapper.writeValueAsString(note))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.patientId").value(1))
                                .andExpect(jsonPath("$.text").value("newNoteText"));
        }

        @Test
        public void testGetNoteById() throws Exception {
                mockMvc.perform(get("/notes/" + note1.getPatientId()))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", Matchers.hasSize(2)));
        }

        @Test
        public void testUpdateNote() throws Exception {
                Note updatedNote = new Note(note1);
                updatedNote.setText("updatedText");
                mockMvc.perform(put("/notes/" + note1.getId())
                                .content(objectMapper.writeValueAsString(
                                                updatedNote))
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.text").value("updatedText"));
        }

        @Test
        public void testDeleteNote() throws Exception {
                mockMvc.perform(delete("/notes/" + note1.getId()))
                                .andExpect(status().isOk());

                mockMvc.perform(get("/notes/" + note1.getPatientId()))
                                .andExpect(jsonPath("$", Matchers.hasSize(1)));
        }
}
