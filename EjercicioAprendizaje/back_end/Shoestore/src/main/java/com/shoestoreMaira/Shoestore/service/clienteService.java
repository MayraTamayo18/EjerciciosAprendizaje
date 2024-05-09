package com.shoestoreMaira.Shoestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.shoestoreMaira.Shoestore.interfaceService.IclienteService;
import com.shoestoreMaira.Shoestore.interfaces.Icliente;
import com.shoestoreMaira.Shoestore.model.cliente;

@Service
public class clienteService implements IclienteService{

    @Autowired
	private Icliente data;
	
	
	@Override
	public String save(cliente cliente) {
		data.save(cliente);
		return cliente.getId_cliente();
	}

	@Override
	public List<cliente> findAll() {
		List<cliente> ListaCliente=
				(List<cliente>) data.findAll();
		//(List<cliente>) : Es un cast
		//ya que findAll() retorna un objeto distinto
		//- Retorna un iterable <cliente>
		//- se convierte a list <cliente>
		return ListaCliente;
	}

	@Override
	public List<cliente>filtroCliente(String filtro) {
		List<cliente>ListaCliente=data.filtroCliente(filtro);
		return ListaCliente;
	}
	
	
	// la variable que almacena los registros
	@Override
	public List<cliente>filtroCedulaClientes(String identificacion ) {
		List<cliente>ListaCliente=data.filtroCedulaCliente(identificacion);
		return ListaCliente;
	}
	
	@Override
	public Optional<cliente> findOne(String id_cliente) {
		Optional<cliente> cliente=data.findById(id_cliente);
		return cliente;
	}

	// @Override
	// public int delete(String id_cliente) {
	// 	data.deleteById(id_cliente);
	// 	return 1;
	// }

	@Override
	public int delete(String id_Cliente) {
		var cliente=data.findById(id_Cliente).get();
		cliente.setEstado("Inactivo"); 
        data.save(cliente); 
		return 0;
	}

	
	
}
