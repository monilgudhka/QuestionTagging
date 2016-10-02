/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monilgudhka.questiontagging.resource;

import javax.ws.rs.Consumes;
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
@Path("domain")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.APPLICATION_JSON)
public class DomainResource {
   
    @GET
    public String getAllDomain(){
        return "Get Domain List";
    }
    
    @GET
    @Path("/{domainId}")
    public String getDomain(@PathParam("domainId") long domainId){
        return "Get Domain";
    }
    
    @POST
    public String addDomain(){
        return "Add Domain";
    }
}
