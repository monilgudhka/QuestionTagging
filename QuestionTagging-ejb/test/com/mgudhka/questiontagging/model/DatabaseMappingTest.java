/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.model;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Monil Gudhka
 */
public class DatabaseMappingTest {
    public DatabaseMappingTest() {}
    
    public static String getRandom(){
        return "" + (new Date()).getTime();
    }
    
    
    
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    
    
    @BeforeClass
    public static void createFactory(){
        entityManagerFactory = Persistence.createEntityManagerFactory("QuestionTagging-ejbPU");
    }
    
    @Before
    public void createManager(){
        entityManager = entityManagerFactory.createEntityManager();
    }
   
    
    
    
    
    
    private void persist(Object obj){
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }
    private <E> E find(Class E, Object id){
        entityManager.getTransaction().begin();
        E objFromDatabase = (E)entityManager.find(E, id);
        entityManager.getTransaction().commit();
        return objFromDatabase;
    }
    
    
    
    
    
    
    @Test
    public void objectsByApplicationAndByORMShouldBeEqual(){
        DomainInfo domainInfo = new DomainInfo("domain qb test", "location qb test " + getRandom());
        QuestionBank bank = new QuestionBank(domainInfo, "bankName");
        bank.setDescription("QuestionBank for Test");
        persist(domainInfo);
        persist(bank);
        QuestionBank bankFromDatabase = find(QuestionBank.class, bank.getId());
        assertEquals("Objects created by Application and ORM should be same", bank, bankFromDatabase);
    }
    
    
    
    
   
    @Test
    public void listShouldBeMaintained(){
        Category subjective = new Category("Subjective " + getRandom());
        
        QuestionType shortAnswer = new QuestionType("shortAnswer " + getRandom());
        shortAnswer.setCategory(subjective);
        QuestionType shortNote = new QuestionType("shortNote " + getRandom());
        shortNote.setCategory(subjective);
        QuestionType longAnswer = new QuestionType("longAnswer " + getRandom());
        longAnswer.setCategory(subjective);
        
        assertEquals("Length of QuestionType in Category should be equals to subjective QuestionTypes", subjective.getQuestionType(), 3);
        
        persist(subjective);
        persist(shortAnswer);
        persist(shortNote);
        persist(longAnswer);
        
        Category categoryFromDatabase = find(Category.class, subjective.getId());
        assertEquals("Category created by application and by ORM should be equal", subjective, categoryFromDatabase);
        assertEquals("QuestionTypes in Category from Database should be same as QuestionTypes added", subjective.getQuestionType(), categoryFromDatabase.getQuestionType());
    }
    
    
    
    
    
    
    
    @Test
    public void stateChangesShouldBeSaved(){
        DomainInfo domainInfo = new DomainInfo("domain test", "location test " + getRandom());
        domainInfo.setVersion(1.1);
        persist(domainInfo);
        
        domainInfo.setVersion(2.1);
        
        DomainInfo domainFromDatabase = find(DomainInfo.class, domainInfo.getId());
        assertEquals("DomainInfo Version from Database and from application should be same", domainInfo.getVersion(), domainFromDatabase.getVersion(), 0.5);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @After
    public void destoryManager(){
        entityManager.close();
    }
    
    @AfterClass
    public static void destroyFactory(){
        entityManagerFactory.close();
    }
}
