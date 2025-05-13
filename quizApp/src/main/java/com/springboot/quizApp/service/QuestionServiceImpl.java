package com.springboot.quizApp.service;

import com.springboot.quizApp.dao.QuestionRepository;
import com.springboot.quizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public String addQuestion(Question question) {


        Question isExists = questionRepository.findById(question.getQuestionNo()).orElse(null);

        if(isExists == null){
            questionRepository.save(question);
            return "Question Saved";
        }else{
            return "Question already present";
        }

    }


    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public String updateQuestion(int id, Question question) {
        Question ifExist = questionRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Id don't match"));

        ifExist.setQuestion(question.getQuestion());
        ifExist.setDifficultyLevel(question.getDifficultyLevel());
        ifExist.setOption1(question.getOption1());
        ifExist.setOption2(question.getOption2());
        ifExist.setOption3(question.getOption3());
        ifExist.setCorrectAnswer(question.getCorrectAnswer());

        questionRepository.save(ifExist);
        return "Updated";

    }

    @Override
    public Question deleteContact(int id) {
        Question question = questionRepository.findById(id).orElseThrow(()->new NoSuchElementException("Id not matched"));

        questionRepository.deleteById(id);
        return question;
    }

    @Override
    public List<Question> getQuestionByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

}
