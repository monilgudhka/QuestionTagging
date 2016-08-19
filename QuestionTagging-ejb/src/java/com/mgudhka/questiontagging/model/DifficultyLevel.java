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
        name = "get_all_DifficultyLevel",
        query = "select e from DifficultyLevel e"
    )
)
@Entity
public class DifficultyLevel implements Serializable, QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @NaturalId
    private String difficultyLevelName;
    private String description;

    @OneToMany(mappedBy = "difficultyLevel")
    private Set<Question> questionSet;
    
    
    
    
    
    protected DifficultyLevel(){
        this.questionSet = new HashSet<>();
    }
    public DifficultyLevel(String difficultyLevelName){
        this();
        this.difficultyLevelName = difficultyLevelName;
    }
    
    
    
    
    
    
    public int getId() {
        return id;
    }
    
    public String getDifficultyLevelName() {
        return difficultyLevelName;
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

    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.difficultyLevelName);
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
        return Objects.equals(this.difficultyLevelName, ((DifficultyLevel) obj).difficultyLevelName);
    }
}
