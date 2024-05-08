package com.shoestoreMaira.Shoestore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="cliente")
public class cliente {
    
    @Id
   @GeneratedValue(strategy = GenerationType.UUID) 
   @Column(name = "id_cliente", nullable = false, length = 36)
	private String id_cliente;

    @Column(name = "tipo_identificacion", nullable = false )
	private String tipo_identificacion;

    @Column(name = "identificacion", nullable = false, length = 10)
	private String identificacion;

    @Column (name = "nombres_cliente", nullable = false, length = 45)
	private String nombres_cliente;
	
	@Column(name = "apellidos_cliente", nullable = false, length = 30)
	private String apellidos_cliente;
	
	@Column(name = "telefono", nullable = false, length = 13)
	private String telefono;
	
	@Column(name = "direccion", nullable = false, length = 45)
	private String direccion;

	@Column(name = "ciudad", nullable = false, length = 45)
	private String ciudad;

    @Column(name = "correo_electronico", nullable = false, length = 100)
	private String correo_electronico;
    
    @Column(name = "estado", nullable = false )
	private String estado;

    public cliente() {
        super(); 
    }

    public cliente(String id_cliente, String tipo_identificacion, String identificacion, 
					String nombres_cliente, String apellidos_cliente, String telefono,
					String direccion, String ciudad, String correo_electronico,  String estado)
	{
		super();
		
		this.id_cliente = id_cliente;
		this.tipo_identificacion = tipo_identificacion;
		this.identificacion = identificacion;
		this.nombres_cliente = nombres_cliente;
		this.apellidos_cliente = apellidos_cliente;
		this.telefono = telefono;
		this.direccion = direccion;
		this.direccion = direccion;
        this.ciudad = ciudad;
        this.correo_electronico = correo_electronico;
        this.estado = estado;
	}


    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres_cliente() {
        return nombres_cliente;
    }

    public void setNombres_cliente(String nombres_cliente) {
        this.nombres_cliente = nombres_cliente;
    }

    public String getApellidos_cliente() {
        return apellidos_cliente;
    }

    public void setApellidos_cliente(String apellidos_cliente) {
        this.apellidos_cliente = apellidos_cliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }
}
