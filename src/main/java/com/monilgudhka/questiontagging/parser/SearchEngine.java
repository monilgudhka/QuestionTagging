/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Monil Gudhka
 */
public class SearchEngine {
  
    
    public static Object search(Dictionary dictionary, String sentence, boolean caseSensitive){
        Object value = null;
        char[] key = sentence.toCharArray();
        int prevIndex = 0;
        Dictionary.Node currentNode = dictionary.getRoot();
        Dictionary.Node previousNode = null;
        
        for(int i=0; i<key.length; i++){
            currentNode = currentNode.getNextNode(key[i], caseSensitive);
            
            if(currentNode == null){
                if(previousNode != null){
                    return previousNode.getValue();
                }
                i = prevIndex;
                prevIndex = i+1;
                currentNode = dictionary.getRoot();
            }else if(currentNode.getValue() != null){
                    previousNode = currentNode;
                    prevIndex = i;
            }
        }
        if(previousNode!=null && previousNode.getValue()!=null){
            return previousNode.getValue();
        }
        return value;
    }
    
    
    public static List<Object> searchAll(Dictionary dictionary, String sentence, boolean caseSensitive){
        List<Object> valueList = new ArrayList<>();
        char[] key = sentence.toCharArray();
        int prevIndex = 0;
        Dictionary.Node currentNode = dictionary.getRoot();
        Dictionary.Node previousNode = null;
        
        for(int i=0; i<key.length; i++){
            currentNode = currentNode.getNextNode(key[i], caseSensitive);
            
            if(currentNode == null){
                if(previousNode != null){
                    valueList.add(previousNode.getValue());
                    previousNode = null;
                }
                i = prevIndex;
                prevIndex = i+1;
                currentNode = dictionary.getRoot();
            }else if(currentNode.getValue() != null){
                    previousNode = currentNode;
                    prevIndex = i;
            }
        }
        if(previousNode!=null && previousNode.getValue()!=null){
            valueList.add(previousNode.getValue());
        }
        
        return valueList;
    }
}
