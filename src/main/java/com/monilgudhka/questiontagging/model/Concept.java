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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author Monil Gudhka
 */
@Entity
public class Concept implements Serializable, QuestionTag{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NaturalId
    private String conceptName;
    @ManyToOne
    private DomainInfo domainInfo;
    
    @ManyToMany(mappedBy = "concept")
    private Set<Question> question;
    
    
    protected Concept(){
        this.question = new HashSet<>();
    }
    public Concept(DomainInfo domainInfo, String conceptName){
        this();
        this.conceptName = conceptName;
        this.setDomainInfo(domainInfo);
    }
    private void setDomainInfo(DomainInfo domainInfo) {
        this.domainInfo = domainInfo;
        this.domainInfo.addConcept(this);
    }
    
    
    
    
    
    
    public long getId() {
        return id;
    }
    public String getConceptName() {
        return conceptName;
    }
    public DomainInfo getDomainInfo() {
        return domainInfo;
    }
    
    
    
    void addQuestion(Question question){
        this.question.add(question);
    }
    void removeQuestion(Question question){
        this.question.remove(question);
    }

    
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.conceptName);
        hash = 23 * hash + Objects.hashCode(this.domainInfo);
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
        Concept other = (Concept) obj;
        return Objects.equals(this.conceptName, other.conceptName) && Objects.equals(this.domainInfo, other.domainInfo);
    }

    
}
