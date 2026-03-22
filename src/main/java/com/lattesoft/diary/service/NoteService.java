package com.lattesoft.diary.service;

import com.lattesoft.diary.model.Day;
import com.lattesoft.diary.model.Note;
import com.lattesoft.diary.repository.DayRepository;
import com.lattesoft.diary.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final DayRepository dayRepository;

    public Note getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    public Note addNoteToDay(Long dayId, String text, Long parentNoteId) {
        Day day = dayRepository.findById(dayId)
                .orElseThrow(() -> new RuntimeException("Day not found."));

        Note createdNote = new Note();
        createdNote.setDay(day);
        createdNote.setText(text);

        day.getNotes().add(createdNote);

        if (parentNoteId != null) {
            Note parentNote = noteRepository.findById(parentNoteId)
                    .orElseThrow(() -> new RuntimeException("Parent note not found."));

            createdNote.setParentNote(parentNote);
            parentNote.getSubNotes().add(createdNote);
        }

        return noteRepository.save(createdNote);
    }

    @Transactional
    public Note updateNote(Long id, String text) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found."));

        LocalDate today = LocalDate.now();

        if (!Objects.equals(note.getDay().getDate(), today)) {
            throw new RuntimeException("Too late to update note");
        }

        note.setText(text);

        return noteRepository.save(note);
    }

    @Transactional
    public void deleteNote(Long noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found."));

        if (note.getParentNote() != null) {
            note.getParentNote().getSubNotes().remove(note);
        }
        if (note.getDay() != null) {
            note.getDay().getNotes().remove(note);
        }

        noteRepository.delete(note);
    }
}
