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

import com.shoestoreMaira.Shoestore.interfaceService.IproductoService;
import com.shoestoreMaira.Shoestore.model.producto;

@RequestMapping("/api/v1/producto")
@RestController
@CrossOrigin
public class productoController {
     @Autowired
	private IproductoService productoService;
	
	@PostMapping("/")
	public ResponseEntity<Object> save(
			@ModelAttribute("producto") producto producto
			){
		productoService.save(producto);
		return new ResponseEntity<>(producto,HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var Listaproducto=productoService.findAll();
		return new ResponseEntity<>(Listaproducto, HttpStatus.OK);
	}
	
	// @GetMapping("/busquedafiltro/{filtro}")
	// public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	// 	var Listaproducto=productoService.filtroproducto(filtro);
	// 	return new ResponseEntity<>(Listaproducto, HttpStatus.OK);
	// }
	
	//@PathVariable : Recibe una variable por enlace
		@GetMapping("/{id_producto}")
		public ResponseEntity<Object> findOne(@PathVariable String id_producto){
			var producto=productoService.findOne(id_producto);
			return new ResponseEntity<>(producto,HttpStatus.OK);
			
		}
		
		@DeleteMapping("/eliminarPermanente/{id_producto}")
		public ResponseEntity<Object> delete(@PathVariable String id_producto){	
			 productoService.delete(id_producto);
					return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
		}
		@PutMapping("/{id_producto}")
		public ResponseEntity<Object> update(@PathVariable String id_producto, @ModelAttribute("producto")producto productoUpdate){
			var producto = productoService.findOne(id_producto).get();
			if (producto != null) {
				producto.setNombre_producto(productoUpdate.getNombre_producto());
				producto.setCantidad(productoUpdate.getCantidad());
				producto.setPrecio(productoUpdate.getPrecio());
				producto.setPorcentaje_iva(productoUpdate.getPorcentaje_iva());
				producto.setPorcentaje_descuento(productoUpdate.getPorcentaje_descuento()); 
				producto.setEstado(productoUpdate.getEstado());
			productoService.save(producto);
			return new ResponseEntity<>(producto, HttpStatus.OK);
			
			}
			else {
				return new ResponseEntity<>("Error producto no encontrado",HttpStatus.BAD_REQUEST);
			}
				
			}
}
