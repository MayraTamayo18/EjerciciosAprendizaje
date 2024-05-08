package com.shoestoreMaira.Shoestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoestoreMaira.Shoestore.interfaceService.IclienteService;
import com.shoestoreMaira.Shoestore.model.cliente;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;




@RequestMapping("/api/v1/cliente")
@RestController
@CrossOrigin
public class clienteContoller {
    @Autowired
	private IclienteService clienteService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(@ModelAttribute("cliente") cliente cliente){
			// condicion para cuando ya exista el  registro 
			  List<cliente>listaClienteValidacion=clienteService.filtroCedulaClientes(cliente.getIdentificacion());
			if(listaClienteValidacion.size()!=0){
				//ya tiene un registro activo
				return new ResponseEntity<>("El cliente ya se encuentra registrado",HttpStatus.BAD_REQUEST);		
			}	
			if (cliente.getTipo_identificacion().equals("")) {
				return new ResponseEntity<>("El tipo documento es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getIdentificacion().equals("")) {
				return new ResponseEntity<>("El número de documento es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getNombres_cliente().equals("")) {
				return new ResponseEntity<>("Los nombres son obligatorios", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getApellidos_cliente().equals("")) {
				return new ResponseEntity<>("los apellidos son obligatorios", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getTelefono().equals("")) {
				return new ResponseEntity<>("El Telefono es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getDireccion().equals("")) {
				return new ResponseEntity<>("la direccion es obligatorio", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getCiudad().equals("")) {
				return new ResponseEntity<>("La ciudad es obligatoria", HttpStatus.BAD_REQUEST);
			}
			if (cliente.getEstado().equals("")) {
				return new ResponseEntity<>("El estado es obligatorio", HttpStatus.BAD_REQUEST);
			}
			
			
		clienteService.save(cliente);
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}
	// @PostMapping("/")
	// public ResponseEntity<Object> save(
	// 		@ModelAttribute("cliente") cliente cliente
	// 		){
	// 	clienteService.save(cliente);
	// 	return new ResponseEntity<>(cliente,HttpStatus.OK);
	// }
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaCliente=clienteService.findAll();
		return new ResponseEntity<>(ListaCliente, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
		var Listacliente=clienteService.filtroCliente(filtro);
		return new ResponseEntity<>(Listacliente, HttpStatus.OK);
	}
	
	//@PathVariable : Recibe una variable por enlace traer datos
		@GetMapping("/{id_cliente}")
		public ResponseEntity<Object> findOne(@PathVariable String id_cliente){
			var cliente=clienteService.findOne(id_cliente);
			return new ResponseEntity<>(cliente,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/eliminarPermanente/{id_cliente}")
		public ResponseEntity<Object> delete(@PathVariable String id_cliente){	
			 clienteService.delete(id_cliente);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}

		// @DeleteMapping("/{id_cliente}") //eliminar
		// public ResponseEntity<Object> delete(@PathVariable String id_cliente){	
		// 	 clienteService.delete(id_cliente);
		// 			return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		// }

		@PutMapping("/{id_cliente}") //actualizar
		public ResponseEntity<Object> update(@PathVariable String id_cliente, @ModelAttribute("cliente")cliente clienteUpdate){
			var cliente = clienteService.findOne(id_cliente).get();
			if (cliente != null) {
				cliente.setTipo_identificacion(clienteUpdate.getTipo_identificacion());
				cliente.setIdentificacion(clienteUpdate.getIdentificacion());
				cliente.setNombres_cliente(clienteUpdate.getNombres_cliente());
				cliente.setApellidos_cliente(clienteUpdate.getApellidos_cliente());
				cliente.setTelefono(clienteUpdate.getTelefono());
				cliente.setCiudad(clienteUpdate.getCiudad());
				cliente.setDireccion(clienteUpdate.getDireccion());
				cliente.setEstado(clienteUpdate.getEstado());
			clienteService.save(cliente);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error cliente no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}
}
