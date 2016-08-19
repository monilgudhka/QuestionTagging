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
        name = "get_all_CongnitiveLevel",
        query = "select e from CongnitiveLevel e"
    )
)
@Entity
public class CongnitiveLevel implements Serializable, QuestionTag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NaturalId
    private String congnitiveLevelName;
    private String description;
    
    @OneToMany(mappedBy = "congnitiveLevel")
    private Set<Question> questionSet;
    @OneToMany(mappedBy = "congnitiveLevel")
    private Set<Keyword> keywordSet;

    
    
    
    
    
    protected CongnitiveLevel(){
        questionSet = new HashSet<>();
        keywordSet = new HashSet<>();
    }
    public CongnitiveLevel(String congnitiveLevelName){
        this();
        this.congnitiveLevelName = congnitiveLevelName;
    }
    
    
    
    
    
    
    
    
    
    public int getId() {
        return id;
    }

    public String getCongnitiveLevelName() {
        return congnitiveLevelName;
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
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.congnitiveLevelName);
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
        return Objects.equals(this.congnitiveLevelName, ((CongnitiveLevel)obj).congnitiveLevelName);
    }
    
}
