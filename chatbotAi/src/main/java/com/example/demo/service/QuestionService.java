package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }
    public Question getQuestionById(Long id) throws Exception{
        return questionRepository.findById(id).orElseThrow(Exception::new);
    }
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
    public void updateQuestion(Long id, Question question) throws Exception {
        Question question1 = questionRepository.findById(id)
                .orElseThrow(() -> new Exception("question not found with id: " + id));
        questionRepository.save(question1);
    }
}
