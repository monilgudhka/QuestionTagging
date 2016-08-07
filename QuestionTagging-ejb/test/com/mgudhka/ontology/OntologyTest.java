/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.ontology;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
public class OntologyTest {
    public OntologyTest() {}
    
    private File owlFile;
    private int nodeCount;
    
    
    @Before
    public void initOWL(){
        owlFile = new File("DomainInfo/DataStructure.owl");
        nodeCount = 58;
    }
    

    @Test
    public void parsingOntology() throws SAXException, IOException {
        Ontology ontology = new Ontology(owlFile);
        ontology.parse();
        Collection<Node> nodeList = ontology.getNodeList();
        assertEquals(nodeCount, nodeList.size());
        Node node = nodeList.iterator().next();
        assertNotNull(node);
        assertNotNull(node.getProperty(Node.ALTERNATIVE).get(0));
    }
    
}
