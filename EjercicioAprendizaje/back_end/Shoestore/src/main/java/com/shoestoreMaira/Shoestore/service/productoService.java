package com.shoestoreMaira.Shoestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoestoreMaira.Shoestore.interfaceService.IproductoService;
import com.shoestoreMaira.Shoestore.interfaces.Iproducto;
import com.shoestoreMaira.Shoestore.model.producto;

@Service
public class productoService implements IproductoService {
    
    @Autowired
	private Iproducto data;
	
	
	@Override
	public String save(producto producto) {
		data.save(producto);
		return producto.getId_producto();
	}

	@Override
	public List<producto> findAll() {
		List<producto> ListaProducto=
				(List<producto>) data.findAll();
		//(List<producto>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <producto>
		//- se convierte a list <producto>
		return ListaProducto;
	}
	
	@Override
	public List<producto> filtroProducto(String filtro) {
		List<producto>ListaProducto=data.filtroProducto(filtro);
		return ListaProducto;
	}
	
	
	@Override
	public Optional<producto> findOne(String id_producto) {
		Optional<producto> producto=data.findById(id_producto);
		return producto;
	}

	@Override
	public int delete(String id_producto) {
		data.deleteById(id_producto);
		return 1;
	}

	
}
