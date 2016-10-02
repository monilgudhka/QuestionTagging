/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.service;

import com.monilgudhka.questiontagging.model.Category;
import com.monilgudhka.questiontagging.model.CongnitiveLevel;
import com.monilgudhka.questiontagging.model.DifficultyLevel;
import com.monilgudhka.questiontagging.model.Keyword;
import com.monilgudhka.questiontagging.model.QuestionBank;
import com.monilgudhka.questiontagging.model.QuestionType;
import com.monilgudhka.questiontagging.model.WHType;
import com.monilgudhka.questiontagging.parser.Dictionary;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.xml.sax.SAXException;

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
        
        init();
    }
    
    private void init(){
        Database database = new Database();
        System.out.println("inside init Model");
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
    
    
    public void selectQuestionBank(QuestionBank questionBank) throws IOException, FileNotFoundException, SAXException{
        if(null != questionBank.getDictionary()){
            return;
        }
        
        
        Dictionary dictionary = new Dictionary();
            
        List<Keyword> keywordList = this.getEntity(Keyword.class);
        for(Keyword keyword: keywordList){
            dictionary.add(keyword.getWord(), keyword);
        }

        List<WHType> whTypeList = this.getEntity(WHType.class);
        for(WHType whType: whTypeList){
            dictionary.add(whType.getWhTypeName(), whType);
        }

        questionBank.getDomain().parseDomain(dictionary);
        questionBank.setDictionary(dictionary);
    }
    
    
    
    
    
    public void close(){
        Database.disconnect();
    }
    
    
    
    
    
    private static <T> List<T> getAllRows(EntityManager entityManager, Class T){
        String namedQuery = "get_all_"+T.getSimpleName();
        System.out.println("Triggering  = " + namedQuery);
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, T);
        return query.getResultList();
    }
}
