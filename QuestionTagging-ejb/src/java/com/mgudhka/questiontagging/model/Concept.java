/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import com.mgudhka.ontology.Node;
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
import javax.persistence.Transient;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author Monil Gudhka
 */
@Entity
public class Concept implements Serializable, QuestionTag{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NaturalId
    private String conceptName;
    @ManyToOne
    private DomainInfo domainInfo;
    
    @ManyToMany(mappedBy = "concept")
    private Set<Question> question;
    
    @Transient
    private Node node;
    
    
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
    
    
    
    
    
    
    public int getId() {
        return id;
    }
    public String getConceptName() {
        return conceptName;
    }
    public DomainInfo getDomainInfo() {
        return domainInfo;
    }
    Node getNode(){
        return this.node;
    }
    
    
    
    void addQuestion(Question question){
        this.question.add(question);
    }
    void removeQuestion(Question question){
        this.question.remove(question);
    }
    void setNode(Node node){
        if(!this.conceptName.equals(node.getName())){
            throw new RuntimeException("Node doesn't match with Concept");
        }
        this.node = node;
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
