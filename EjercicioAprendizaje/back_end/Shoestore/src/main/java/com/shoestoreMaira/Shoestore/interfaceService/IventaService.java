package com.shoestoreMaira.Shoestore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoestoreMaira.Shoestore.model.venta;

@Service
public interface IventaService {
    public String save (venta venta);
	public List<venta>findAll();
	// public List<venta> filtroFechas(String filtro);
    // public List<venta> filtroVenta(String filtro);
	public Optional<venta> findOne(String id_Venta);
	public int delete(String id_Venta);
}
