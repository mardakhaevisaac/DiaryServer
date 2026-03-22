package com.lattesoft.diary.controller;

import com.lattesoft.diary.model.Note;
import com.lattesoft.diary.model.NoteRequest;
import com.lattesoft.diary.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter @Setter
class NoteUpdateRequest {
    private String text;
}

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notes")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/{dayId}")
    public ResponseEntity<Note> add(@PathVariable Long dayId, @RequestBody NoteRequest request) {
        return ResponseEntity.ok(noteService.addNoteToDay(
                dayId, request.getText(), request.getParentNoteId()
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody NoteUpdateRequest request) {
        return ResponseEntity.ok(noteService.updateNote(id, request.getText()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
