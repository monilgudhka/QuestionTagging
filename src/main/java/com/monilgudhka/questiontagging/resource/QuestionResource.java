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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author monil
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource {
    
    @GET
    public String getAllQuestion(){
        return "Get Question lsit";
    }
    
    @GET
    @Path("/{questionId}")
    public String getQuestion(@PathParam("questionId") long questionId){
        return "Get Question";
    }
    
    @POST
    public String addQuestion(){
        return "Add Question";
    }
    
    @PUT
    @Path("/{questionId}")
    public String updateQuestion(@PathParam("questionId") long questionId){
        return "Update Question";
    }
    
    @DELETE
    @Path("/{questionId}")
    public String deleteQuestion(@PathParam("questionId") long questionId){
        return "Delete Question";
    }
}
