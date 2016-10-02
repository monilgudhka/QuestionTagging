/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author Monil Gudhka
 */

@NamedQueries(
    @NamedQuery(
        name = "get_all_QuestionType",
        query = "select e from QuestionType e"
    )
)
@Entity
public class QuestionType implements Serializable, QuestionTag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NaturalId
    private String questionTypeName;
    @ManyToOne
    private Category category;
    private String description;
    
    @OneToMany(mappedBy = "questionType")
    private Set<Question> questionSet;
    @OneToMany(mappedBy = "questionType")
    private Set<Keyword> keywordSet;
    
    
    
    
    
    
    protected QuestionType(){
        questionSet = new HashSet<>();
        keywordSet = new HashSet<>();
    }
    public QuestionType(String questionTypeName){
        this();
        this.questionTypeName = questionTypeName;
    }
    
    
    
    
    
    public long getId() {
        return id;
    }

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        if(this.category != null){
            this.category.removeQuestionType(this);
        }
        this.category = category;
        this.category.addQuestionType(this);
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    void addQuestion(Question question){
        this.questionSet.add(question);
    }
    void removeQuestion(Question question){
        this.questionSet.remove(question);
    }
    
    void addKeyword(Keyword keyword){
        this.keywordSet.add(keyword);
    }
    void removeKeyword(Keyword keyword){
        this.keywordSet.remove(keyword);
    }
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.questionTypeName);
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
        return Objects.equals(this.questionTypeName, ((QuestionType) obj).questionTypeName);
    }
}