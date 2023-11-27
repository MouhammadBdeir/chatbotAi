package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 10000)
    public String question;
    @OneToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;
    @Column
    private LocalDateTime creationDateTime;
    @PrePersist
    protected void onCreate() {
        creationDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        creationDateTime.format(formatter);
    }

    public Question(Long id, String question, Answer answer, LocalDateTime creationDateTime) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.creationDateTime = creationDateTime;
    }
    public Question( String question, Answer answer, LocalDateTime creationDateTime) {
        this.question = question;
        this.answer = answer;
        this.creationDateTime = creationDateTime;
    }
    public Question() {
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
