/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import com.mgudhka.questiontagging.parser.Dictionary;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
@NamedQueries(
    @NamedQuery(
        name = "get_all_QuestionBank",
        query = "select e from QuestionBank e"
    )
)
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
    
    void createDictionary() throws IOException, FileNotFoundException, SAXException{
        this.dictionary = new Dictionary();
        this.domainInfo.parseDomain(dictionary);
    }
}
