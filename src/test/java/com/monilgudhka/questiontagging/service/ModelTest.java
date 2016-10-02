/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.service;

import com.monilgudhka.questiontagging.model.Category;
import com.monilgudhka.questiontagging.model.CongnitiveLevel;
import com.monilgudhka.questiontagging.model.DifficultyLevel;
import com.monilgudhka.questiontagging.model.QuestionBank;
import com.monilgudhka.questiontagging.model.QuestionType;
import com.monilgudhka.questiontagging.model.WHType;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.xml.sax.SAXException;

/**
 *
 * @author Monil Gudhka
 */
public class ModelTest {
    
    public ModelTest() {}

    private Model model;
    
    @Before
    public void createModel(){
        model = Model.getInstance();
    }
    
    
    @Test
    public void testLoading() {
        assertEquals(10, model.getEntity(WHType.class).size());
        assertEquals(7, model.getEntity(CongnitiveLevel.class).size());
        assertEquals(4, model.getEntity(DifficultyLevel.class).size());
        assertEquals(3, model.getEntity(Category.class).size());
        assertEquals(13, model.getEntity(QuestionType.class).size());
    }
    
    @Test
    public void testSelectQuestionBank(){
        List<QuestionBank> questionBankList = model.getEntity(QuestionBank.class);
        if(questionBankList.isEmpty()){
            fail("QuestionBankList should not be empty");
        }
        QuestionBank questionBank = questionBankList.get(0);
        assertNull(questionBank.getDictionary());
        
        try {
            model.selectQuestionBank(questionBank);
        } catch (IOException | SAXException ex) {
            fail("Exception " + ex);
        }
        
        assertNotNull(questionBank.getDictionary());
    }
    
    @After
    public void closeModel(){
        //model.close();
    }
    
}
