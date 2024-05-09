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
   	public ResponseEntity<Object> save(@ModelAttribute("ventas") venta venta){
			// condicion para cuando ya exista el  registro 
			//   List<ventas>listaventasValidacion=ventasService.filtroCedulaventas(ventas.getNumero_documento());
			// if(listaventasValidacion.size()!=0){
			// 	//ya tiene un registro activo
			// 	return new ResponseEntity<>("El ventas ya se encuentra registrado",HttpStatus.BAD_REQUEST);		
			// }	
			// if (ventas.getTotal().equals("")) {
			// 	return new ResponseEntity<>("El número de documento es obligatorio", HttpStatus.BAD_REQUEST);
			// }
			if (venta.getFecha_venta().equals("")) {
				return new ResponseEntity<>("la fecha de venta es obligatoria", HttpStatus.BAD_REQUEST);
			}
			if (venta.getEstado().equals("")) {
                return new ResponseEntity<>("El estado del producto es obligatorio", HttpStatus.BAD_REQUEST);
            }
			
		ventaService.save(venta);
		return new ResponseEntity<>("Se guardo correctamente",HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaVenta=ventaService.findAll();
		return new ResponseEntity<>(ListaVenta, HttpStatus.OK);
	}
	
	// @GetMapping("/busquedafiltro/{filtro}")
	// public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
	// 	var ListaVenta=ventaService.filtroVenta(filtro);
	// 	return new ResponseEntity<>(ListaVenta, HttpStatus.OK);
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
