/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Estudiante;
import com.model.Materia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Juan Neira
 */
@Stateless
@Path("com.model.estudiante")
public class EstudianteFacadeREST extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "practica_bimestralPU")
    private EntityManager em;
    
    @EJB
    MateriaFacadeREST materiaFacadeREST;
    
    public EstudianteFacadeREST() {
        super(Estudiante.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Estudiante entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Estudiante entity) {
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
    public Estudiante find(@PathParam("id") Integer id) {
        return super.find(id);
    }

//    @GET
//    @Override
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public List<Estudiante> findAll() {
//        return super.findAll();
//    }
    
    //servicio web para crear registros no repetidos//
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String crear(@FormParam("nombres") String nombres, @FormParam("eliminado") boolean eliminado, @FormParam("fkmateria") int fkmateria) {
        String mensaje = "{\"exitoso\":false}";
        Materia objeto= materiaFacadeREST.find(fkmateria);
        try {
            create(new Estudiante(nombres, eliminado, objeto)); 
            mensaje = "{\"exitoso\":true}";
        } catch (Exception e) {
            System.out.println(e);
        }
        return mensaje;
    }
    
    //servicio web para leer registros//
    @POST
    @Path("leer")
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Estudiante> findAll() {
        return super.findAll();
    }
    
    //servicio web para editar registros//
    @POST
    @Path("editar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String editar(@FormParam("idestudiante") int idestudiante, @FormParam("nombres") String nombre, @FormParam("fkmateria") int fkmateria) {
        String mensaje = "{\"exitoso\":false, \"motivo\":";
        //crear objeto de la clase ingresar//
        Estudiante u = editarporidestudiante(idestudiante);
        Materia objeto=materiaFacadeREST.find(fkmateria);
        if (u != null) {
            u.setNombres(nombre);
            u.setFkMateria(objeto);
            try {
                edit(idestudiante, u);
                mensaje = "{\"exitoso\":true}";
            } catch (Exception e) {
                mensaje += "\"Excepcion en base\"";
            }
        }
        else{
               mensaje += "\"Datos no correctos\"";
        }
        mensaje += "}";
        return mensaje;
    }
    
    //servicio web para elimiar//
    @POST
    @Path("eliminar")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public String eliminar(@FormParam("idestudiante") int idestudiante) {
        String mensaje = "{\"exitoso\":false, \"motivo\":";
        //crear objeto de la clase ingresar//
        Estudiante u = eliminarregistro(idestudiante);
        if (u != null) {
            u.setEliminado(true);
            try {
                edit(idestudiante, u);
                mensaje = "{\"exitoso\":true}";
            } catch (Exception e) {
                mensaje += "\"Excepcion en base\"";
            }
        }
        else{
               mensaje += "\"Datos no correctos\"";
        }
        mensaje += "}";
        return mensaje;
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Estudiante> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    //metodo para buscar y editar por id_empleado//
    public Estudiante editarporidestudiante(int idestudiante){
        TypedQuery<Estudiante> gry;
        gry= getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante and e.eliminado = :eliminado", Estudiante.class);
        gry.setParameter("idEstudiante", idestudiante);
        gry.setParameter("eliminado", false);
        try{
            return gry.getSingleResult();//objeto de tipo ingresar//
        }catch(NoResultException e){
            return null;
        }
    }
    
    //metodo para buscar para id_estudiante//
    public Estudiante eliminarregistro(int idestudiante){
        TypedQuery<Estudiante> gry;
        gry= getEntityManager().createQuery("SELECT e FROM Estudiante e WHERE e.idEstudiante = :idEstudiante and e.eliminado = :eliminado", Estudiante.class);
        gry.setParameter("idEstudiante", idestudiante);
        gry.setParameter("eliminado", false);
        try{
            return gry.getSingleResult();//objeto de tipo ingresar//
        }catch(NoResultException e){
            return null;
        }
    }
}
