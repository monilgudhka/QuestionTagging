/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.parser.Dictionary;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Monil Gudhka
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQueries(
    @NamedQuery(
        name = "get_all_QuestionBank",
        query = "select e from QuestionBank e"
    )
)
@Entity
public class QuestionBank implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String questionBankName;
    private String description;
    
    @XmlTransient
    @ManyToOne
    private DomainInfo domainInfo;

    @XmlTransient
    @OneToMany(mappedBy = "questionBank")
    private Set<Question> questionSet;
    
    @XmlTransient
    @Transient
    private Dictionary dictionary;

    
    
    
    
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
    
    
    
    
    
    
    
    
    
    
    
    public long getId() {
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
    
    public Dictionary getDictionary() {
        return dictionary;
    }
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    
    
    
    void addQuestion(Question question){
        this.questionSet.add(question);
    }
    void removeQuestion(Question question){
        this.questionSet.remove(question);
    }
}
