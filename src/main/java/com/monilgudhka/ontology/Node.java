/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.ontology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Monil Gudhka
 */
public class Node {
    public static final String ALTERNATIVE = "#alternative";
    
    private final String name;
    private final Map<String, List<Object>> propertyMap;
    
    public Node(String name){
        this.name = name;
        this.propertyMap = new HashMap<>();
    }
    
    public String getName() {
        return name;
    }
    public void addProperty(String property, Object value){
        List<Object> valueList;
        if(this.propertyMap.containsKey(property)){
            valueList = this.propertyMap.get(property);
        }else{
            valueList = new ArrayList<>();
            this.propertyMap.put(property, valueList);
        }
        valueList.add(value);
    }
    public List<Object> getProperty(String property){
        List<Object> valueList = this.propertyMap.get(property);
        return (valueList==null)? new ArrayList() : valueList;
    }
}
