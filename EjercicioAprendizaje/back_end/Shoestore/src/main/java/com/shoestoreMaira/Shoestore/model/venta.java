package com.shoestoreMaira.Shoestore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="venta")
public class venta {
     @Id
   @GeneratedValue(strategy = GenerationType.UUID) 
   @Column(name = "id_venta", nullable = false, length = 36)
	private String id_venta;

    @Column(name = "total", nullable = false )
	private double total;
    

    @Column(name = "estado", nullable = false )
	private String estado;

    @Column(name = "fecha_venta", nullable = false )
	private LocalDateTime fecha_venta;

    @ManyToOne
	@JoinColumn(name = "id_cliente")
	private cliente cliente;

    

    public venta() {
        super(); 
    }
    public venta(String id_venta, double total, String estado, String id_cliente,LocalDateTime fecha_venta ) 
	{
		super();
		
		this.id_venta = id_venta;
		this.total = total;
		this.estado = estado;
        this.fecha_venta = fecha_venta;
    }

    public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public cliente getCliente() {
        return cliente;
    }
    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }
    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }
    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

}
