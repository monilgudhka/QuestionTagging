/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.service;

import com.mgudhka.questiontagging.model.Category;
import com.mgudhka.questiontagging.model.CongnitiveLevel;
import com.mgudhka.questiontagging.model.DifficultyLevel;
import com.mgudhka.questiontagging.model.QuestionType;
import com.mgudhka.questiontagging.model.WHType;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
        model.init();
        
        assertEquals(10, model.getEntity(WHType.class).size());
        assertEquals(7, model.getEntity(CongnitiveLevel.class).size());
        assertEquals(4, model.getEntity(DifficultyLevel.class).size());
        assertEquals(3, model.getEntity(Category.class).size());
        assertEquals(13, model.getEntity(QuestionType.class).size());
    }
    
    @After
    public void closeModel(){
        model.close();
    }
    
}
