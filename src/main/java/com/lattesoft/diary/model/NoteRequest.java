package com.lattesoft.diary.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NoteRequest {
    private String text;
    private Long parentNoteId;
}
