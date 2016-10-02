/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.model;

import com.monilgudhka.questiontagging.parser.Dictionary;
import com.monilgudhka.questiontagging.parser.SearchEngine;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
public class DomainInfoTest {
    
    public DomainInfoTest() {}

    private Dictionary dictionary;
    
    @Before
    public void createDictionary(){
        dictionary = new Dictionary();
    }
    
    
    @Test
    public void testParseDomainProvideExistingFile() throws IOException, FileNotFoundException, SAXException {
        DomainInfo domainInfo = new DomainInfo("Test", "DataStructure.owl");
        domainInfo.parseDomain(dictionary);
        assertEquals("Concept should be same as that of Ontology", 58, domainInfo.getConcept().size());
        Concept concept = domainInfo.getConcept().iterator().next();
        assertEquals(concept, SearchEngine.search(dictionary, concept.getConceptName(), false));
    }
    
    @Test(expected=FileNotFoundException.class)
    public void testParseDomainProvideNonExistingFile() throws IOException, FileNotFoundException, SAXException {
        DomainInfo domainInfo = new DomainInfo("Test", "NotExist.owl");
        domainInfo.parseDomain(dictionary);
        fail("FileNotFoundException should be thrown");
    }
    
    @Test(expected=SAXException.class)
    public void testParseDomainProvideInvalidFile() throws IOException, FileNotFoundException, SAXException {
        DomainInfo domainInfo = new DomainInfo("Test", "Invalid.owl");
        domainInfo.parseDomain(dictionary);
        fail("FileNotFoundException should be thrown");
    }
}
