/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.parser;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Monil Gudhka
 */
public class SearchEngineTest {
    
    public SearchEngineTest() {}

    private Dictionary dictionary;
    
    @Before
    public void createDataMap(){
        dictionary = new Dictionary();
        String[] dataList = new String[]{"Array", "Linked List", "Linked Map", "Linked List Map", "Doubly Linked List"};
        for(String data : dataList){
            dictionary.add(data, data);
        }
    }
    
    
    @Test
    public void singleSentenceSimple() {
        String sentence = "Array";
        Object actual = SearchEngine.search(dictionary, sentence, false);
        assertEquals(sentence, actual);
    }
    
    
    @Test
    public void singleSentenceBranch() {
        String sentence = "Linked Map";
        Object actual = SearchEngine.search(dictionary, sentence, false);
        assertEquals(sentence, actual);
    }
    
    
    @Test
    public void singleSentenceDiffStart() {
        String sentence = "Doubly Linked List";
        Object actual = SearchEngine.search(dictionary, sentence, false);
        assertEquals(sentence, actual);
    }
    
   
    @Test
    public void singleSentenceLong() {
        String sentence = "Linked List Map";
        Object actual = SearchEngine.search(dictionary, sentence, false);
        assertEquals(sentence, actual);
    }
    
    
    @Test
    public void singleSentenceCaseSensitive() {
        String sentence = "linked list map";
        Object actual = SearchEngine.search(dictionary, sentence, true);
        assertNull(actual);
    }
    
    
    @Test
    public void singleSentenceCaseInSensitive() {
        String sentence = "linked list map";
        String actual = (String)SearchEngine.search(dictionary, sentence, false);
        assertNotNull(actual);
        assertNotEquals(sentence, actual);
        assertEquals(sentence, actual.toLowerCase());
    }
    
    
    @Test
    public void multiSentenceSimple() {
        String sentence = "Array";
        
        List expected = new ArrayList();
        expected.add("Array");
        
        List actual = SearchEngine.searchAll(dictionary, sentence, false);    
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    
    
    @Test
    public void multiSentenceBranch() {
        String sentence = "Linked Map Linked List";
        
        List expected = new ArrayList();
        expected.add("Linked Map");
        expected.add("Linked List");
        
        List actual = SearchEngine.searchAll(dictionary, sentence, false);    
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    
    @Test
    public void multiSentenceSequence() {
        String sentence = "Linked List Linked Map Array";
        
        List expected = new ArrayList();
        expected.add("Linked List");
        expected.add("Linked Map");
        expected.add("Array");
        
        List actual = SearchEngine.searchAll(dictionary, sentence, false);    
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    
    @Test
    public void multiSentenceAbsentWord() {
        String sentence = "Linked Stack SLinked Map Array";
        
        List expected = new ArrayList();
        expected.add("Linked Map");
        expected.add("Array");
        
        List actual = SearchEngine.searchAll(dictionary, sentence, false);    
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    
    @Test
    public void multiSentenceLong() {
        String sentence = "Linked Stack SLinked List Map Array Doubly Linked List";
        
        List expected = new ArrayList();
        expected.add("Linked List Map");
        expected.add("Array");
        expected.add("Doubly Linked List");
        
        List actual = SearchEngine.searchAll(dictionary, sentence, false);    
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    
    @Test
    public void multiSentenceCaseSensitive() {
        String sentence = "linked List Linked map aRRay";
        
        List expected = new ArrayList();
        
        List actual = SearchEngine.searchAll(dictionary, sentence, true);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
    
    
    @Test
    public void multiSentenceCaseInSensitive() {
        String sentence = "linked List Linked map aRRay";
        
        List expected = new ArrayList();
        expected.add("Linked List");
        expected.add("Linked Map");
        expected.add("Array");
        
        List actual = SearchEngine.searchAll(dictionary, sentence, false);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
