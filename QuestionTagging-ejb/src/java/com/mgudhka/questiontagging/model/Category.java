/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
        name = "get_all_Category",
        query = "select e from Category e"
    )
)
@Entity
public class Category implements Serializable, QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NaturalId
    private String categoryName;
    
    @OneToMany(mappedBy = "category")
    private Set<QuestionType> questionTypeSet;
    
    
    
    
    
    
    protected Category(){
        this.questionTypeSet = new HashSet<>();
    }
    public Category(String categoryName){
        this();
        this.categoryName = categoryName;
    }
    
    
    
    
    
    public int getId() {
        return id;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    
    

    void addQuestionType(QuestionType questionType){
        this.questionTypeSet.add(questionType);
    }
    void removeQuestionType(QuestionType questionType){
        this.questionTypeSet.remove(questionType);
    }
    int getQuestionType(){
        return this.questionTypeSet.size();
    }
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.categoryName);
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
        return Objects.equals(this.categoryName, ((Category) obj).categoryName);
    }
    
}
