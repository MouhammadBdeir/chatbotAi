package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 10000)
    public String answer;
    @Column
    private LocalDateTime creationDateTime;

    @OneToOne(mappedBy = "answer", cascade = CascadeType.ALL)
    private Question question;

    public Answer(Long id, String answer, LocalDateTime creationDateTime, Question question) {
        this.id = id;
        this.answer = answer;
        this.creationDateTime = creationDateTime;
        this.question = question;
    }
    public Answer( String answer, LocalDateTime creationDateTime, Question question) {
        this.answer = answer;
        this.creationDateTime = creationDateTime;
        this.question = question;
    }
    public Answer() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @PrePersist
    protected void onCreate() {
        creationDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        creationDateTime.format(formatter);
    }

}
