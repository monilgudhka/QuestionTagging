/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author Monil Gudhka
 */
@NamedQueries(
    @NamedQuery(
        name = "get_all_Keyword",
        query = "select e from Keyword e"
    )
)
@Entity
public class Keyword implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NaturalId
    private String word;
    @ManyToOne
    private CongnitiveLevel congnitiveLevel;
    @ManyToOne
    private QuestionType questionType;

    protected Keyword() {
    }

    public Keyword(String word, CongnitiveLevel congnitiveLevel, QuestionType questionType) {
        this.word = word;
        this.setCongnitiveLevel(congnitiveLevel);
        this.setQuestionType(questionType);
    }

    private void setCongnitiveLevel(CongnitiveLevel congnitiveLevel) {
        this.congnitiveLevel = congnitiveLevel;
        this.congnitiveLevel.addKeyword(this);
    }

    private void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
        this.questionType.addKeyword(this);
    }

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public CongnitiveLevel getCongnitiveLevel() {
        return congnitiveLevel;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.word);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.word, ((Keyword) obj).word);
    }

}
