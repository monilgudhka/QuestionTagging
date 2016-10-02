/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.service;

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
    private static final String PERSISTENCE_UNIT_NAME = "QuestionTagging_PU";
    private static class EntityManagerFactoryHolder {
        private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        private static final Collection<Database> ENTITY_MANAGER_LIST = new ArrayBlockingQueue<>(20);
    }
    
    private static EntityManagerFactory connect(){
        return EntityManagerFactoryHolder.ENTITY_MANAGER_FACTORY;
    }
    static void disconnect(){
        System.out.println(EntityManagerFactoryHolder.ENTITY_MANAGER_LIST.size());
        for(Database database : EntityManagerFactoryHolder.ENTITY_MANAGER_LIST){
            database.close();
        }
        EntityManagerFactoryHolder.ENTITY_MANAGER_FACTORY.close();
    }
    
    
    
    
    private EntityManager entityManager;
    Database(){
        System.out.println("inside  Database");
        init();
    }
    private void init(){
        this.entityManager = connect().createEntityManager();
        System.out.println("inside init Database");
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
            this.commit();
        }
        this.entityManager.close();
        EntityManagerFactoryHolder.ENTITY_MANAGER_LIST.remove(this);
    }
}
