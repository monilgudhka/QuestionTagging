/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.ontology;

import com.mgudhka.owlparser.OwlHandler;
import com.mgudhka.xml.XMLParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
public class Ontology {
    
    private final Map<String, Node> nodeMap;
    private final File owlFile;
    
    public Ontology(File owlFile){
        this.owlFile = owlFile;
        this.nodeMap = new HashMap<>();
    }
    
    
    public void parse() throws IOException, FileNotFoundException, SAXException{
        OwlHandler handler = new OwlHandler(this);
        XMLParser.parse(this.getOwlFile(), handler);
    }
    
    public File getOwlFile() {
        return owlFile;
    }
    
    public Collection<Node> getNodeList(){
        return nodeMap.values();
    }
    public Node getNode(String nodeName){
        return nodeMap.get(nodeName);
    }
    public void addNode(Node node){
        nodeMap.put(node.getName(), node);
    }
}
