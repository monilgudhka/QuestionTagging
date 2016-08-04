/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.owlparser;

import com.mgudhka.ontology.Ontology;
import com.mgudhka.xml.XMLHandler;
import java.util.Stack;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
public class OwlHandler extends XMLHandler{
    
    private final Stack<Element> elementStack;
    private final Ontology ontology;
    public OwlHandler(Ontology ontology) {
        elementStack = new Stack();
        this.ontology = ontology;
        this.elementStack.push(ElementFactory.getElement("FirstElement"));
    }

    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        Element element = ElementFactory.getElement(localName);
        element.startElement(ontology, elementStack.peek(), qName, atts);
        elementStack.push(element);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        elementStack.pop().endElement(ontology, elementStack.peek(), qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementStack.peek().characters(ontology, elementStack.get(elementStack.size()-2), new String(ch, start, length));
    }
}
