/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.owlparser;

import com.mgudhka.ontology.Ontology;
import java.util.Objects;
import org.xml.sax.Attributes;

/**
 *
 * @author Monil Gudhka
 */
class Element {
    
    private final String elementName;
    
    Element(){
        this.elementName = this.getClass().getSimpleName();
    }
    Element(String elementName){
        this.elementName = elementName;
    }

    
    public String getElementName() {
        return elementName;
    }
    
    
    

    public void startElement(Ontology ontology, Element parentElement, String qName, Attributes atts) {}

    public void endElement(Ontology ontology, Element parentElement, String qName) {}

    public void characters(Ontology ontology, Element parentElement, String character) {}
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.elementName);
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
        return Objects.equals(this.elementName, ((Element) obj).elementName);
    }

    
}
