/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Neira
 */
@Entity
@Table(name = "informacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informacion.findAll", query = "SELECT i FROM Informacion i")
    , @NamedQuery(name = "Informacion.findByIdVehiculo", query = "SELECT i FROM Informacion i WHERE i.idVehiculo = :idVehiculo")
    , @NamedQuery(name = "Informacion.findByCategoria", query = "SELECT i FROM Informacion i WHERE i.categoria = :categoria")
    , @NamedQuery(name = "Informacion.findByPlaca", query = "SELECT i FROM Informacion i WHERE i.placa = :placa")
    , @NamedQuery(name = "Informacion.findByMarca", query = "SELECT i FROM Informacion i WHERE i.marca = :marca")
    , @NamedQuery(name = "Informacion.findByModelo", query = "SELECT i FROM Informacion i WHERE i.modelo = :modelo")
    , @NamedQuery(name = "Informacion.findByColor", query = "SELECT i FROM Informacion i WHERE i.color = :color")
    , @NamedQuery(name = "Informacion.findByEliminado", query = "SELECT i FROM Informacion i WHERE i.eliminado = :eliminado")
    , @NamedQuery(name = "Informacion.findByFechacreacion", query = "SELECT i FROM Informacion i WHERE i.fechacreacion = :fechacreacion")})
public class Informacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Categoria")
    private String categoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Placa")
    private String placa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Modelo")
    private String modelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Color")
    private String color;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Eliminado")
    private boolean eliminado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Fecha_creacion")
    private String fechacreacion;

    public Informacion() {
    }

    public Informacion(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Informacion(Integer idVehiculo, String categoria, String placa, String marca, String modelo, String color, boolean eliminado, String fechacreacion) {
        this.idVehiculo = idVehiculo;
        this.categoria = categoria;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.eliminado = eliminado;
        this.fechacreacion = fechacreacion;
    }
    
    //contructor para crear registros//
    public Informacion(String categoria, String placa, String marca, String modelo, String color, boolean eliminado) {
        this.categoria = categoria;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.eliminado = eliminado;
    }
    
    
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Informacion)) {
            return false;
        }
        Informacion other = (Informacion) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.model.Informacion[ idVehiculo=" + idVehiculo + " ]";
    }
    
}
