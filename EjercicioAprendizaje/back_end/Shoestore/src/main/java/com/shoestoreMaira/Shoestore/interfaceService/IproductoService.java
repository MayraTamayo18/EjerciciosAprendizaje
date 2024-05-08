package com.shoestoreMaira.Shoestore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoestoreMaira.Shoestore.model.producto;

@Service
public interface IproductoService {
    public String save (producto producto);
	public List<producto>findAll();
	// public List<producto> filtroproducto(String filtro);
	public Optional<producto> findOne(String id_producto);
	public int delete(String id_producto);
}
