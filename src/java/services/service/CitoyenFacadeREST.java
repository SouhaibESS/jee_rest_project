/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import jpa.Citoyen;

/**
 *
 * @author souhaib
 */
@Stateless
@Path("jpa.citoyen")
public class CitoyenFacadeREST extends AbstractFacade<Citoyen> {

    @PersistenceContext(unitName = "CitoyenServiceRESTfromDBPU")
    private EntityManager em;

    public CitoyenFacadeREST() {
        super(Citoyen.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Citoyen entity) {
        List<Citoyen> citoyens = super.findByName(entity.getNom());
        if(citoyens.isEmpty()) {
            super.create(entity);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Citoyen entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Citoyen find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Citoyen> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("findByIdNomPrenom-{id}-{nom}-{prenom}")
    @Produces({"application/xml", "application/json"})
    public List<Citoyen> findByIdNamePrenom(@PathParam("id") int id, @PathParam("nom") String nom, @PathParam("prenom") String prenom) {
        //return super.findByIDNamePrenom_Criteria(id, nom, prenom);
        //return super.findByIDNamePrenom_JPQL(id, nom, prenom);
        //return super.findByIDNamePrenom_JPQL_NamedQuery(id, nom, prenom);
        return super.findByIDNamePrenom_SQL_NativeQuery(id, nom, prenom);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Citoyen> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @PUT
    @Path("id/{id}/nvadresse/{adresse}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int updateAdresse(@PathParam("id") Integer id, @PathParam("adresse")String adresse) {
        try{
            return super.updateAdresse_Criteria(id, adresse);
        }catch(Exception e){
            return -1;
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
