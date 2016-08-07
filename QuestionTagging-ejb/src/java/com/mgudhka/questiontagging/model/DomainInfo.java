/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import com.mgudhka.ontology.Node;
import com.mgudhka.ontology.Ontology;
import com.mgudhka.questiontagging.parser.Dictionary;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
@NamedQueries(
    @NamedQuery(
        name = "get_all_DomainInfo",
        query = "select e from DomainInfo e"
    )
)
@Entity
public class DomainInfo implements Serializable  {
    private static final File DOMAIN_DIRECTORY = new File("DomainInfo");
    
    
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
    Set<Concept> getConcept(){
        return this.conceptSet;
    }

    
    void parseDomain(Dictionary dictionary) throws IOException, FileNotFoundException, SAXException{
        Ontology ontology = new Ontology(new File(DOMAIN_DIRECTORY, this.location));
        ontology.parse();
        for(Iterator<Node> itr = ontology.getNodeList().iterator(); itr.hasNext(); ){
            Node node = itr.next();
            Concept concept = new Concept(this, node.getName());
            dictionary.add(node.getName(), concept);
            for(Object alternateName: node.getProperty(Node.ALTERNATIVE)){
                dictionary.add(alternateName.toString(), concept);
            }
        }
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
