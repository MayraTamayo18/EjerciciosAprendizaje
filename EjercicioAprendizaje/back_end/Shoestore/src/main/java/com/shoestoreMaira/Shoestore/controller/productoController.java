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

            if (producto.getNombre_producto().equals("")) {
                return new ResponseEntity<>("El nombre del producto es obligatorio", HttpStatus.BAD_REQUEST);
            }
            if (producto.getDescripcion().equals("")) {
                return new ResponseEntity<>("La descripción del producto es obligatorio", HttpStatus.BAD_REQUEST);
            }
            if (producto.getCantidad()==0) {
                return new ResponseEntity<>("Porfavor, digite la cantidad que desea", HttpStatus.BAD_REQUEST);
            }
            if (producto.getPrecio()==0) {
                return new ResponseEntity<>("Porfavor, digite el precio del producto", HttpStatus.BAD_REQUEST);
            }
            if (producto.getPorcentaje_iva()==0) {
                return new ResponseEntity<>("El porcentaje del iva es obligatorio", HttpStatus.BAD_REQUEST);
            }
            if (producto.getPorcentaje_descuento()==0) {
                return new ResponseEntity<>("El porcentaje del descuento es obligatorio", HttpStatus.BAD_REQUEST);
            }
            if (producto.getEstado().equals("")) {
                return new ResponseEntity<>("El estado del producto es obligatorio", HttpStatus.BAD_REQUEST);
            }
        productoService.save(producto);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }

	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaProducto=productoService.findAll();
		return new ResponseEntity<>(ListaProducto, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
		var ListaProducto=productoService.filtroProducto(filtro);
		return new ResponseEntity<>(ListaProducto, HttpStatus.OK);
	}
	
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
