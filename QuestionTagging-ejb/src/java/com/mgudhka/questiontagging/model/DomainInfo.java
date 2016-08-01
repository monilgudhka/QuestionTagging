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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Monil Gudhka
 */
@Entity
public class DomainInfo implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String domainName;
    @Column(unique = true)
    private String location;
    private double version;
    
    @OneToMany(mappedBy = "domainInfo")
    private Set<QuestionBank> questionBankSet;
    @OneToMany(mappedBy = "domainInfo")
    private Set<Concept> conceptSet;
    
    
    protected DomainInfo(){
        this.questionBankSet = new HashSet<>();
        this.conceptSet = new HashSet<>();
    }
    public DomainInfo(String domainName, String location){
        this();
        this.domainName = domainName;
        this.location = location;
    }
    
    
    
    
    
    public int getId() {
        return id;
    }
    public String getDomainName() {
        return domainName;
    }
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    public String getLocation() {
        return location;
    }
    public double getVersion() {
        return version;
    }
    public void setVersion(double version) {
        this.version = version;
    }
    
    
    
    
    
    void addQuestionBank(QuestionBank questionBank){
        this.questionBankSet.add(questionBank);
    }
    void removeQuestionBank(QuestionBank questionBank){
        this.questionBankSet.remove(questionBank);
    }
    void addConcept(Concept concept){
        this.conceptSet.add(concept);
    }
    void removeConcept(Concept concept){
        this.conceptSet.remove(concept);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.location);
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
        return Objects.equals(this.location, ((DomainInfo) obj).location);
    }
    
}
