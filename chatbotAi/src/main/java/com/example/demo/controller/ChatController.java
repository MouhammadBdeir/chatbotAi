package com.example.demo.controller;

import com.example.demo.dto.ChatGptRequest;
import com.example.demo.dto.ChatGptResponse;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.service.AnswerService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Controller
@RequestMapping("api/chat")
public class ChatController {
    @Value("${OPENAI_Model}")
    private String model;
    @Value("${OPENAI_API_KEY}")
    private String key;
    @Value("${OPENAI_API_URL}")
    private String url;
    @Autowired
    private  RestTemplate template;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public ChatController( QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService=answerService;
    }

    public String chat( String prompt){
        ChatGptRequest request = new ChatGptRequest(model,prompt);
        ChatGptResponse chatGptResponse=template.postForObject(url,request, ChatGptResponse.class);
        assert chatGptResponse != null;
        return chatGptResponse.getChoices().get(0).getMessage().getContent();
    }
    @GetMapping("/ask")
    public String askChatGPT(Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        List<Answer> answers =answerService.getAllAnswers();
        model.addAttribute("answers", answers);
        return "chat";
    }
    @PostMapping("/ask")
    public String askChatGPT(@RequestParam String userMessage) {
        Question question= new Question();
        question.setQuestion(userMessage);
        // Rufen Sie die Antwort von der OpenAI GPT-3-API ab
        String answerText = chat(userMessage);
        Answer answer= new Answer();
        answer.setAnswer(answerText);
        answerService.createAnswer(answer);
        question.setAnswer(answerService.createAnswer(answer));
        questionService.createQuestion(question);
        return "redirect:/api/chat/ask";
    }


}


