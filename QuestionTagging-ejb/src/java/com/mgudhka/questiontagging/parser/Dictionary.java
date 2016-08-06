/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.parser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * DataMap implements Data Structure which is used as the knowledge of Parser.
 * Parser can store and search for data word by word.
 * 
 * @author Monil Gudhka
 */
public class Dictionary{
    
    private final Node root;
    public Dictionary(){
        root = new Node(' ');
    }
    
    /**
     * Stores the word along with data represented by that word
     * @param key
     * @param value
     */
    public void add(String key, Object value){
        Node currentNode = root;
        Node previousNode;
        for(char ch: key.toCharArray()){
            previousNode = currentNode;
            currentNode = currentNode.getNextNode(ch, true);
            if(currentNode == null){
                Node newNode = new Node(ch);
                previousNode.addNode(newNode);
                currentNode = newNode;
            }
        }
        currentNode.setValue(value);
    }
    
    /**
     * Search for the word and returns data represented by that word
     * @param sentence
     * @param caseSensitive
     * @return
     */
    public Object search(String sentence, boolean caseSensitive){
        return search(sentence, root, caseSensitive);
    }
    private Object search(String sentence, Node currentNode, boolean caseSensitive){
        if(!sentence.isEmpty()){
            char key = sentence.charAt(0);
            String remainingSentence = sentence.substring(1);
            Node nextNode = currentNode.getNextNode(key, caseSensitive);
            if(nextNode != null){
                Object value = search(remainingSentence, nextNode, caseSensitive);
                return (value==null)? nextNode.getValue(): value;
            }
        }
        return null;
    }
    
    
    public List<Object> searchAll(String sentence, boolean caseSensitive){
        List<Object> valueList = new ArrayList<>();
        char[] key = sentence.toCharArray();
        int prevIndex = 0;
        Node currentNode = root;
        Node previousNode = null;
        
        for(int i=0; i<key.length; i++){
            currentNode = currentNode.getNextNode(key[i], caseSensitive);
            
            if(currentNode == null){
                if(previousNode != null){
                    valueList.add(previousNode.getValue());
                    previousNode = null;
                }
                i = prevIndex;
                prevIndex = i+1;
                currentNode = root;
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

    
    
    
    
    
    @Override
    public String toString() {
        return root.getStringForDisplay("\t");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static class Node{
        private final char key;
        private final Map<Character, Node> nextNodeMap;
        private Object value;
        
        Node(char key){
            this(key, null);
        }
        Node(char key, Object value){
            this.key = key;
            this.value = value;
            this.nextNodeMap = new LinkedHashMap<>();
        }
        
        void setValue(Object value){
            this.value = value;
        }
        Object getValue(){
            return this.value;
        }
        Node getNextNode(char key, boolean caseSensitive){
            Node nextNode = this.nextNodeMap.get(key);
            if(nextNode==null && !caseSensitive){
                key = (Character.isLowerCase(key))? Character.toUpperCase(key) : Character.toLowerCase(key);
                nextNode = this.nextNodeMap.get(key);
            }
            return nextNode;
        }
        void addNode(Node node){
            this.nextNodeMap.put(node.key, node);
        }

        String getStringForDisplay(String intent){
            StringBuilder display = new StringBuilder();
            display.append(intent).append(this.key).append("=").append(this.value);
            intent = intent+"\t";
            for (Entry<Character, Node> nextNode : this.nextNodeMap.entrySet()) {
                display.append("\n").append(nextNode.getValue().getStringForDisplay(intent));
            }
            return display.toString();
        }
    }
}
