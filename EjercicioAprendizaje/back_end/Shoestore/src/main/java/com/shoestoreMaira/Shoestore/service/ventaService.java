package com.shoestoreMaira.Shoestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoestoreMaira.Shoestore.interfaceService.IventaService;
import com.shoestoreMaira.Shoestore.interfaces.Iventa;
import com.shoestoreMaira.Shoestore.model.venta;

@Service
public class ventaService implements IventaService{
    @Autowired
	private Iventa data;
	
	
	@Override
	public String save(venta venta) {
		data.save(venta);
		return venta.getId_venta();
	}

	@Override
	public List<venta> findAll() {
		List<venta> ListaVenta=
				(List<venta>) data.findAll();
		//(List<venta>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <venta>
		//- se convierte a list <venta>
		return ListaVenta;
	}
	
	// @Override
	// public List<venta> filtroVenta(String filtro) {
	// 	List<venta>ListaVenta=data.filtroVenta(filtro);
	// 	return ListaVenta;
	// }
	// @Override
	// public List<venta> filtroFechas(String filtro) {
	// 	List<venta>ListaVenta=data.filtroFechas(filtro);
	// 	return ListaVenta;
	// }

	
	@Override
	public Optional<venta> findOne(String id_venta) {
		Optional<venta> venta=data.findById(id_venta);
		return venta;
	}

	@Override
	public int delete(String id_venta) {
		data.deleteById(id_venta);
		return 1;
	}

}
