/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author monil
 */
@Path("bank")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionBankResource {
    
    @GET
    public String getAllQuestionBank(){
        return "Get Question Bank List";
    }
    
    @GET
    @Path("/{questionBankId}")
    public String getQuestionBank(@PathParam("questionBankId") long questionBankId){
        return "Get Question Bank";
    }
    
    @POST
    public String addQuestionBank(){
        return "Add Question Bank";
    }
    
    @DELETE
    @Path("/{questionBankId}")
    public String deleteQuestionBank(@PathParam("questionBankId") long questionBankId){
        return "Delete Question Bank List";
    }
    
    
    
    @Path("/{questionBankId}/question")
    public QuestionResource getQuestionResource(){
        return new QuestionResource();
    }
}
