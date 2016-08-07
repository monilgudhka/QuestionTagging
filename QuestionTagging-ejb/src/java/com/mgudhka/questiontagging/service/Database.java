/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.service;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Monil Gudhka
 */
class Database {
    private static final String PERSISTENCE_UNIT_NAME = "QuestionTagging-ejbPU";
    private static class EntityManagerFactoryHolder {
        private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        private static final Collection<Database> ENTITY_MANAGER_LIST = new ArrayBlockingQueue<>(5);
    }
    
    private static EntityManagerFactory connect(){
        return EntityManagerFactoryHolder.ENTITY_MANAGER_FACTORY;
    }
    static void disconnect(){
        for(Database database : EntityManagerFactoryHolder.ENTITY_MANAGER_LIST){
            database.close();
        }
        EntityManagerFactoryHolder.ENTITY_MANAGER_FACTORY.close();
    }
    
    
    
    
    private EntityManager entityManager;
    Database(){
        init();
    }
    private void init(){
        this.entityManager = connect().createEntityManager();
        EntityManagerFactoryHolder.ENTITY_MANAGER_LIST.add(this);
    }
    EntityManager begin(){
        this.entityManager.getTransaction().begin();
        return this.entityManager;
    }
    void commit(){
        this.entityManager.getTransaction().commit();
    }
    void close(){
        if(this.entityManager.getTransaction().isActive()){
            System.out.println("inside commit");
            this.commit();
        }
        this.entityManager.close();
        EntityManagerFactoryHolder.ENTITY_MANAGER_LIST.remove(this);
    }
}
