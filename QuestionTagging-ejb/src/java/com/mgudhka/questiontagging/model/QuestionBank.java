/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Monil Gudhka
 */
@Entity
public class QuestionBank implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String questionBankName;
    private String description;
    @ManyToOne
    private DomainInfo domainInfo;

    @OneToMany(mappedBy = "questionBank")
    private Set<Question> questionSet;
    
    
    
    
    
    protected QuestionBank(){
        this.questionSet = new HashSet<>();
    }
    public QuestionBank(DomainInfo domainInfo, String questionBankName){
        this();
        this.questionBankName = questionBankName;
        this.setDomainInfo(domainInfo);
    }
    private void setDomainInfo(DomainInfo domainInfo) {
        this.domainInfo = domainInfo;
        this.domainInfo.addQuestionBank(this);
    }
    
    
    
    
    
    
    
    
    
    
    
    public int getId() {
        return id;
    }
    
    public String getQuestionBankName() {
        return questionBankName;
    }
    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public DomainInfo getDomain() {
        return domainInfo;
    }

    
    
    
    void addQuestion(Question question){
        this.questionSet.add(question);
    }
    void removeQuestion(Question question){
        this.questionSet.remove(question);
    }
}
