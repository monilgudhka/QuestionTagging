/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author monil
 */
public class DomainInfoTest {
    
    public DomainInfoTest() {}

    
    @Test
    public void testParseDomainProvideExistingFile() throws IOException, FileNotFoundException, SAXException {
        DomainInfo domainInfo = new DomainInfo("Test", "DataStructure.owl");
        domainInfo.parseDomain();
        assertEquals("Concept should be same as that of Ontology", 58, domainInfo.getConcept().size());
        Concept concept = domainInfo.getConcept().iterator().next();
        assertEquals("Concept and Node should match", concept.getConceptName(), concept.getNode().getName());
    }
    
    @Test(expected=FileNotFoundException.class)
    public void testParseDomainProvideNonExistingFile() throws IOException, FileNotFoundException, SAXException {
        DomainInfo domainInfo = new DomainInfo("Test", "NotExist.owl");
        domainInfo.parseDomain();
        fail("FileNotFoundException should be thrown");
    }
    
    @Test(expected=SAXException.class)
    public void testParseDomainProvideInvalidFile() throws IOException, FileNotFoundException, SAXException {
        DomainInfo domainInfo = new DomainInfo("Test", "Invalid.owl");
        domainInfo.parseDomain();
        fail("FileNotFoundException should be thrown");
    }
}
