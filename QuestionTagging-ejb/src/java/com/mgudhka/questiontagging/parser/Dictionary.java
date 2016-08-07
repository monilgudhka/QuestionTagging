/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgudhka.questiontagging.parser;

import java.util.LinkedHashMap;
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
    private int size;
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
        this.size++;
    }
    
    public int size(){
        return this.size;
    }

    Node getRoot(){
        return this.root;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return root.getStringForDisplay("\t");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    static class Node{
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
