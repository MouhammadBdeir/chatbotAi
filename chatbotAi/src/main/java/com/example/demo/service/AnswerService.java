package com.example.demo.service;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.repository.AnswerRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class AnswerService {
    @Autowired
    private Validator validator;

    @Autowired
    AnswerRepository answerRepository;
    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }
    public Answer saveAnswer(Answer answer){
        return answerRepository.save(answer);
    }
    public Answer getAnswerById(Long id) throws Exception{
        return answerRepository.findById(id).orElseThrow(Exception::new);
    }
    public Answer createAnswer(Answer answer) {
        Set<ConstraintViolation<Answer>> violations = validator.validate(answer);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return answerRepository.save(answer
        );
    }
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
    public void updateAnswer(Long id, Answer answer) throws Exception {
        Answer answer1 = answerRepository.findById(id)
                .orElseThrow(() -> new Exception("Answer not found with id: " + id));
        answerRepository.save(answer1);
    }
}
