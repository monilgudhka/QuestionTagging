/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.parser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Monil Gudhka
 */
public class DictionaryTest {
    
    public DictionaryTest() {}

    @Test
    public void testAddition(){
        Dictionary dictionary = new Dictionary();
        dictionary.add("A", "A");
        dictionary.add("B", "B");
        dictionary.add("C", "C");
        assertEquals(3, dictionary.size());
    }
}
