package com.lattesoft.diary.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "day_id")
    @JsonBackReference(value = "day-note")
    private Day day;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference(value = "parent-child")
    private Note parentNote;

    @OneToMany(mappedBy = "parentNote", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "parent-child")
    private List<Note> subNotes = new ArrayList<>();
}
