/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.service;

import com.mgudhka.questiontagging.model.Category;
import com.mgudhka.questiontagging.model.CongnitiveLevel;
import com.mgudhka.questiontagging.model.DifficultyLevel;
import com.mgudhka.questiontagging.model.Keyword;
import com.mgudhka.questiontagging.model.QuestionBank;
import com.mgudhka.questiontagging.model.QuestionType;
import com.mgudhka.questiontagging.model.WHType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Monil Gudhka
 */
public class Model {
    
    private static class ModelHolder {
        private static final Model INSTANCE = new Model();
    }
    public static Model getInstance() {
        return ModelHolder.INSTANCE;
    }
    
    
    private final List<Class> entityToLoad;
    private final Map<Class, List> entityObject;
    
    private Model() {
        entityToLoad = new ArrayList<>();
        entityToLoad.add(WHType.class);
        entityToLoad.add(CongnitiveLevel.class);
        entityToLoad.add(DifficultyLevel.class);
        entityToLoad.add(Category.class);
        entityToLoad.add(QuestionType.class);
        entityToLoad.add(Keyword.class);
        entityToLoad.add(QuestionBank.class);
        entityObject = new HashMap<>();
    }
    
    public void init(){
        Database database = new Database();
        EntityManager entityManager = database.begin();
        for(Class T : entityToLoad){
            entityObject.put(T, getAllRows(entityManager, T));
        }
        database.commit();
        database.close();
    }
    
    public <T> List<T> getEntity(Class T){
        return entityObject.get(T);
    }
    
    
    public void close(){
        Database.disconnect();
    }
    
    
    
    
    
    private static <T> List<T> getAllRows(EntityManager entityManager, Class T){
        String namedQuery = "get_all_"+T.getSimpleName();
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, T);
        return query.getResultList();
    }
}