package com.shoestoreMaira.Shoestore.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoestoreMaira.Shoestore.model.cliente;

@Service
public interface IclienteService {
	public String save (cliente cliente);
	public List<cliente>findAll();
	public List<cliente> filtroCliente(String filtro);
	public List<cliente>filtroCedulaClientes(String identificacion);
	public Optional<cliente> findOne(String id_cliente);
	public int delete(String id_cliente);
}
