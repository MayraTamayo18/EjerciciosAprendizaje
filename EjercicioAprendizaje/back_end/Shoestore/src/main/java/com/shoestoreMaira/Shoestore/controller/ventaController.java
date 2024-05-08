package com.shoestoreMaira.Shoestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.shoestoreMaira.Shoestore.interfaceService.IventaService;
import com.shoestoreMaira.Shoestore.model.venta;


@RequestMapping("/api/v1/venta")
@RestController
@CrossOrigin
public class ventaController {
      @Autowired
	private IventaService ventaService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(
			@ModelAttribute("venta") venta venta
			){
		ventaService.save(venta);
		return new ResponseEntity<>(venta,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var Listaventa=ventaService.findAll();
		return new ResponseEntity<>(Listaventa, HttpStatus.OK);
	}
	
	// @GetMapping("/busquedafiltro/{filtro}")
	// public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	// 	var Listaventa=ventaService.filtroventa(filtro);
	// 	return new ResponseEntity<>(Listaventa, HttpStatus.OK);
	// }
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id_venta}")
		public ResponseEntity<Object> findOne(@PathVariable String id_venta){
			var venta=ventaService.findOne(id_venta);
			return new ResponseEntity<>(venta,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/eliminarPermanente/{id_venta}")
		public ResponseEntity<Object> delete(@PathVariable String id_venta){	
			 ventaService.delete(id_venta);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}
		@PutMapping("/{id_venta}")
		public ResponseEntity<Object> update(@PathVariable String id_venta, @ModelAttribute("venta")venta ventaUpdate){
			var venta = ventaService.findOne(id_venta).get();
			if (venta != null) {
				venta.setTotal(ventaUpdate.getTotal());
				venta.setEstado(ventaUpdate.getEstado());
			ventaService.save(venta);
			return new ResponseEntity<>(venta, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error venta no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}

	
	
		
}
