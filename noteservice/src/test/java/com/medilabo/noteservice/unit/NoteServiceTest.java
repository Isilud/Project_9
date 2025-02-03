package com.medilabo.noteservice.unit;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.repository.NoteRepository;
import com.medilabo.noteservice.service.NoteService;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    public NoteService noteService;

    @BeforeEach
    public void setup() {
        noteService = new NoteService(noteRepository);
    }

    @Test
    public void testAddNote() {
        // Given
        Note note = new Note(0, "defaultText");
        note.setId("1");

        // When
        when(noteRepository.save(note)).thenReturn(note);

        // Then
        Note result = noteService.addNote(note);
        assertEquals(0, result.getPatientId());
        assertEquals("defaultText", result.getText());
        verify(noteRepository).save(note);
    }

    @Test
    public void testGetAllNotes() {
        // Given
        Note note1 = new Note(0, "defaultText");
        note1.setId("1");

        Note note2 = new Note(0, "defaultText2");
        note2.setId("2");

        // When
        when(noteRepository.findByPatientId(0)).thenReturn(Arrays.asList(note1, note2));

        // Then
        List<Note> result = noteService.getNotesByPatientId(0);
        assertEquals(2, result.size());
        assertEquals("defaultText", result.get(0).getText());
        assertEquals("defaultText2", result.get(1).getText());
        verify(noteRepository).findByPatientId(0);
    }

    @Test
    public void testUpdateNote() {
        // Given
        Note note = new Note(0, "defaultText");
        note.setId("1");

        // When
        when(noteRepository.save(note)).thenReturn(note);

        // Then
        Note result = noteService.updateNote(note);
        assertNotNull(result);
        assertEquals("defaultText", result.getText());
        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void testDeleteNote() {
        // Given
        String id = "1";

        // When
        doNothing().when(noteRepository).deleteById(id);

        // Then
        noteService.deleteNoteById(id);
        verify(noteRepository, times(1)).deleteById(id);
    }
}