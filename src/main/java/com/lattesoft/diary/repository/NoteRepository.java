package com.lattesoft.diary.repository;

import com.lattesoft.diary.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
