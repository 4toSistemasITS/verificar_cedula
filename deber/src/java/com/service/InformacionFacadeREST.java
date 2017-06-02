/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.Informacion;
import java.util.List;
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
@Path("com.model.informacion")
public class InformacionFacadeREST extends AbstractFacade<Informacion> {

    @PersistenceContext(unitName = "deberPU")
    private EntityManager em;

    public InformacionFacadeREST() {
        super(Informacion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Informacion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Informacion entity) {
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
    public Informacion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Informacion> findAll() {
        return super.findAll();
    }
    
    //metodo para crear registros no repetidos//
    @POST
    @Path("crear")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
     public String crear(@FormParam("categoria") String categoria, @FormParam("placa") String placa, @FormParam("marca") String marca, @FormParam("modelo") String modelo, @FormParam("color") String color, @FormParam("eliminado") boolean eliminado, @FormParam("fechacreacion") String fechacreacion  ){
         String mensaje="{\"exitoso\":false}";
         Informacion c=verificar_datos(categoria, placa, marca, modelo, color, eliminado, fechacreacion);
         if (c==null){
             try{
                 create(new Informacion(0,categoria, placa, marca, modelo, color, eliminado, fechacreacion));
                 mensaje = "{\"exitoso\":true}";
             } catch (Exception e){
                 System.out.println(e);
             }
         }
         return mensaje;
     }
     
     //post para obtener registro por categoria//
    @POST
    @Path("consultacategoria")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Informacion> consulta_categoria(@FormParam("categoria") String categoria) {
        List<Informacion> retorno=obtenerporcategoria(categoria);
        return retorno;
    }
    
    //post para obtener registro por marca//
    @POST
    @Path("consultamarca")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Informacion> consulta_marca(@FormParam("marca") String marca) {
        List<Informacion> retorno=obtenerpormarca(marca);
        return retorno;
    }
    
    //post para obtener registro por eliminado//
    @POST
    @Path("consultaeliminado")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Informacion> consulta_eliminado(@FormParam("eliminado") boolean eliminado) {
        List<Informacion> retorno=obtenerporelimiado(eliminado);
        return retorno;
    }
    
    //post para obtener registro por fecha de creacion//
    @POST
    @Path("consultafechacreacion")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON})
    public List<Informacion> consulta_fechacreacion(@FormParam("fechacreacion") String fecha_creacion) {
        List<Informacion> retorno=obtenerporfechacreacion(fecha_creacion);
        return retorno;
    }
    
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Informacion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    //metodo para verificar datos de registros para cear/
    public Informacion verificar_datos(String categoria, String placa, String marca, String modelo, String color, boolean eliminado, String fechacreacion){
        TypedQuery<Informacion> gry;
        gry= getEntityManager().createQuery("SELECT i FROM Informacion i WHERE i.categoria = :categoria and i.placa = :placa and i.marca = :marca and i.modelo = :modelo and i.color = :color and i.eliminado = :eliminado and i.fechacreacion = :fechacreacion", Informacion.class);
        gry.setParameter("categoria", categoria);
        gry.setParameter("placa", placa);
        gry.setParameter("marca", categoria);
        gry.setParameter("modelo", modelo);
        gry.setParameter("color", color);
        gry.setParameter("eliminado", eliminado);
        gry.setParameter("fechacreacion", fechacreacion);
        try{
            return gry.getSingleResult();
        }catch(NoResultException e){
            return null;
        } 
    }
    
    //metodo para obtener registro por categorias//
    List<Informacion> obtenerporcategoria(String categoria){
        TypedQuery<Informacion> gry;
        gry=getEntityManager().createQuery("SELECT i FROM Informacion i WHERE i.categoria = :categoria", Informacion.class);
        gry.setParameter("categoria", categoria);
        try{
            return gry.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    //metodo para obtener registro por marca//
    List<Informacion> obtenerpormarca(String marca){
        TypedQuery<Informacion> gry;
        gry=getEntityManager().createQuery("SELECT i FROM Informacion i WHERE i.marca = :marca", Informacion.class);
        gry.setParameter("categoria", marca);
        try{
            return gry.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    //metodo para obtener registro por eliminado//
    List<Informacion> obtenerporelimiado(boolean eliminado){
        TypedQuery<Informacion> gry;
        gry=getEntityManager().createQuery("SELECT i FROM Informacion i WHERE i.eliminado = :eliminado", Informacion.class);
        gry.setParameter("eliminado", eliminado);
        try{
            return gry.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
    //metodo para obtener registro por fechacreacion//
    List<Informacion> obtenerporfechacreacion(String fechacreacion){
        TypedQuery<Informacion> gry;
        gry=getEntityManager().createQuery("SELECT i FROM Informacion i WHERE i.fechacreacion = :fechacreacion", Informacion.class);
        gry.setParameter("eliminado", fechacreacion);
        try{
            return gry.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
}
