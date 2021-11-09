/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import models.Carta;

/**
 *
 * @author david
 */
@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private Date fecha;
    private String estado;
    private Long producto_id;

    public Pedido() {
    }

    public Pedido(Long id, String nombre, double precio, Date fecha, String estado, Long producto_id) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fecha = fecha;
        this.estado = estado;
        this.producto_id = producto_id;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(Long producto_id) {
        this.producto_id = producto_id;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", fecha=" + fecha + ", estado=" + estado + ", producto_id=" + producto_id + '}';
    }
    
}
