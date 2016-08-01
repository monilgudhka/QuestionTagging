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
import javax.persistence.OneToMany;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author Monil Gudhka
 */
@Entity
public class WHType implements Serializable, QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NaturalId
    private String whTypeName;
    
    @OneToMany(mappedBy = "whType")
    private Set<Question> questionSet;

    
    protected WHType(){
        questionSet = new HashSet<>();
    }
    public WHType(String whTypeName){
        this();
        this.whTypeName = whTypeName;
    }
    
    
    
    
    
    
    
    public int getId() {
        return id;
    }
    
    public String getWhTypeName() {
        return whTypeName;
    }
    
    void addQuestion(Question question){
        this.questionSet.add(question);
    }
    void removeQuestion(Question question){
        this.questionSet.remove(question);
    }

    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.whTypeName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj==null || getClass()!=obj.getClass()) {
            return false;
        }
        return Objects.equals(this.whTypeName, ((WHType)obj).whTypeName);
    }
}
