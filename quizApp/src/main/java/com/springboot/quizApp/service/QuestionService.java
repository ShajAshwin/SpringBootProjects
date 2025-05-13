package com.springboot.quizApp.service;

import com.springboot.quizApp.entity.Question;

import java.util.List;

public interface QuestionService {

    public String addQuestion(Question question);
    public List<Question> getAllQuestions();
    public String updateQuestion(int id, Question question);
    public Question deleteContact(int id);

    List<Question> getQuestionByCategory(String category);
}
