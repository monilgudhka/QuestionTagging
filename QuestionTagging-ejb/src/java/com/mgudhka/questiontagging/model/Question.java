/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Monil Gudhka
 */
@Entity
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    private QuestionBank questionBank;
    private String questionText;
    @ManyToOne
    private CongnitiveLevel congnitiveLevel;
    @ManyToOne
    private QuestionType questionType;
    @ManyToOne
    private WHType whType;
    @ManyToOne
    private DifficultyLevel difficultyLevel;
    @ManyToMany
    private Set<Concept> concept;

    

    

    protected Question(){
        this.concept = new HashSet<>();
    }
    public Question(QuestionBank questionBank, String questionText){
        this();
        this.questionText = questionText;
        this.setQuestionBank(questionBank);
    }

    private void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
        this.questionBank.addQuestion(this);
    }

    

    
    
    
    
    public int getId() {
        return id;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public String getQuestionText() {
        return questionText;
    }

    public CongnitiveLevel getCongnitiveLevel() {
        return congnitiveLevel;
    }
    public void setCongnitiveLevel(CongnitiveLevel congnitiveLevel) {
        if(this.congnitiveLevel != null){
            this.congnitiveLevel.removeQuestion(this);
        }
        this.congnitiveLevel = congnitiveLevel;
        this.congnitiveLevel.addQuestion(this);
    }

    public QuestionType getQuestionType() {
        return questionType;
    }
    public void setQuestionType(QuestionType questionType) {
        if(this.questionType != null){
            this.questionType.removeQuestion(this);
        }
        this.questionType = questionType;
        this.questionType.addQuestion(this);
    }

    public WHType getWhType() {
        return whType;
    }
    public void setWhType(WHType whType) {
        if(this.whType != null){
            this.whType.removeQuestion(this);
        }
        this.whType = whType;
        this.whType.addQuestion(this);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        if(this.difficultyLevel != null){
            this.difficultyLevel.removeQuestion(this);
        }
        this.difficultyLevel = difficultyLevel;
        this.difficultyLevel.addQuestion(this);
    }

    

    
    public void addConcept(Concept concept){
        this.concept.add(concept);
        concept.addQuestion(this);
    }
    public void removeConcept(Concept concept){
        this.concept.remove(concept);
        concept.removeQuestion(this);
    }
    
    
    
    
    
    
    
    
    
   
    
    
    
}
