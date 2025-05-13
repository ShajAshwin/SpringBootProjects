package com.springboot.quizApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questiondb")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="question_no")
    private int questionNo;
    @Column(name="question")
    private String question;
    @Column(name = "difficulty")
    private Level difficultyLevel;
    @Column(name = "option_1")
    private String option1;
    @Column(name = "option_2")
    private String option2;
    @Column(name = "option_3")
    private String option3;
    @Column(name = "correct_Answer")
    private String correctAnswer;
    @Column(name = "category")
    private String category;

    public Question(String question, Level difficultyLevel, String option1, String option2, String option3, String correctAnswer) {
        this.question = question;
        this.difficultyLevel = difficultyLevel;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnswer = correctAnswer;
    }
}

