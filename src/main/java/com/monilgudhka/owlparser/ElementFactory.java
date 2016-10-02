/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.owlparser;

import com.monilgudhka.ontology.Node;
import com.monilgudhka.ontology.Ontology;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;

/**
 *
 * @author Monil Gudhka
 */
class ElementFactory {
    private static final Map<String, String> NORMALIZE;
    static{
        NORMALIZE = new HashMap<>();
        NORMALIZE.put("Class", "IRI");
    }
    
    private static String normalize(String name){
        return (NORMALIZE.containsKey(name))? NORMALIZE.get(name) : name;
    }
    
    public static Element getElement(String elementName) {
        
        try {
            elementName = normalize(elementName);
            Class elementClass = Class.forName("com.monilgudhka.owlparser."+elementName);
            return (Element) elementClass.newInstance();
        } catch (NullPointerException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            //Logger.getLogger(ElementFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Element(elementName);
    }
}























class IRI extends Element{

    @Override
    public void startElement(Ontology ontology, Element parentElement, String qName, Attributes atts) {
        switch(parentElement.getElementName()){
            case "Declaration": ontology.addNode(new Node(atts.getValue("IRI")));
        }
    }

    @Override
    public void characters(Ontology ontology, Element parentElement, String character) {
        switch(parentElement.getElementName()){
            case "AnnotationAssertion": ((AnnotationAssertion)parentElement).addNodeName(character);
        }
    }
}





class AnnotationAssertion extends Element{
    private String annotation;
    private final List<String> nodeNameList = new ArrayList();
    private String value;
    
    void setAnnotation(String annotation){
        this.annotation = annotation;
    }
    void addNodeName(String nodeName){
        this.nodeNameList.add(nodeName);
    }
    void setValue(String value){
        this.value = value;
    }

    @Override
    public void endElement(Ontology ontology, Element parentElement, String qName) {
        Node node = ontology.getNode(nodeNameList.get(0));
        if(nodeNameList.size() > 1){
            node.addProperty(annotation, nodeNameList.get(1));
        }else{
            node.addProperty(annotation, value);
        }
    }
}




class AnnotationProperty extends Element{

    @Override
    public void startElement(Ontology ontology, Element parentElement, String qName, Attributes atts) {
        switch(parentElement.getElementName()){
            case "AnnotationAssertion": ((AnnotationAssertion)parentElement).setAnnotation(atts.getValue("IRI"));
        }
    }
    
}




class Literal extends Element{
    @Override
    public void characters(Ontology ontology, Element parentElement, String character) {
        switch(parentElement.getElementName()){
            case "AnnotationAssertion": ((AnnotationAssertion)parentElement).setValue(character);
        }
    }
}