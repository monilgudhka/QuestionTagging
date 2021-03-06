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
        name = "get_all_WHType",
        query = "select e from WHType e"
    )
)
@Entity
public class WHType implements Serializable, QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
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
    
    
    
    
    
    
    
    public long getId() {
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
