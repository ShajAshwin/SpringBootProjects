package com.springboot.quizApp.controller;

import com.springboot.quizApp.entity.Question;
import com.springboot.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/addQuestion")
    public ResponseEntity<String> readQuestions(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("/getQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.FOUND);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return new ResponseEntity<>(questionService.getQuestionByCategory(category),HttpStatus.FOUND);
    }

    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable int id,@RequestBody Question question){
        return new ResponseEntity<>(questionService.updateQuestion(id,question),HttpStatus.OK);
    }

    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id){
        return new ResponseEntity<>(questionService.deleteContact(id),HttpStatus.OK);
    }


}
