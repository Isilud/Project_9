package com.medilabo.noteservice.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.aot.generate.Generated;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.medilabo.noteservice.model.Note;
import com.medilabo.noteservice.repository.NoteRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Generated
@Profile("dev") // Cette configuration est active uniquement en mode dev
public class NoteDataConfig {

        private final NoteRepository noteRepository;

        public NoteDataConfig(NoteRepository noteRepository) {
                this.noteRepository = noteRepository;
        }

        @PostConstruct
        public void insertTestData() {
                List<Note> testData = Arrays.asList(
                                new Note(1,
                                                "Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé"),
                                new Note(2,
                                                " Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement"),
                                new Note(2,
                                                "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale"),
                                new Note(3,
                                                "Le patient déclare qu'il fume depuis peu"),
                                new Note(3,
                                                "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé"),
                                new Note(4,
                                                "Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments"),
                                new Note(4,
                                                "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"),
                                new Note(4,
                                                "Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé"),
                                new Note(4,
                                                "Taille, Poids, Cholestérol, Vertige et Réaction"));

                noteRepository.saveAll(testData);
        }
}