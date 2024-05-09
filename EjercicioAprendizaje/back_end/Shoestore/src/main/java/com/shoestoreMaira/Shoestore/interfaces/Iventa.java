package com.shoestoreMaira.Shoestore.interfaces;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.shoestoreMaira.Shoestore.model.venta;

@Repository
public interface Iventa extends CrudRepository<venta,String>{

//     @Query("SELECT c FROM producto c WHERE "
// 	+ "c.nombre_producto LIKE %?1% OR "
// 	+ "c.estado LIKE %?1% ")

// List<venta> filtroVenta(@Param("filtroVenta")String filtro);
//     @Query("SELECT v FROM venta v JOIN v.cliente c WHERE c.id_cliente LIKE=?1 OR"
//     +"c.nombres_cliente LIKE %?1% OR  AND v.fecha_venta LIKE %?1%")

// List<venta> filtroVenta(@Param("filtroVenta")String filtro);
// @Query("SELECT v FROM venta v JOIN" 
//      +" v.cliente c " 
//      + " WHERE v.id_cliente LIKE %?1% "
// 	 + " OR v.nombres_cliente LIKE %?1% ")

//   List<venta> filtroVenta(@Param("filtroVenta")String filtro);

//   List<venta> filtroFechas(String filtro);

// 	@Query ("SELECT * FROM venta"
//           +" WHERE fecha_venta BETWEEN :fechaInicio AND :fechaFin;
// 			")
// 			List<venta> filtroFechaIngreso(LocalDateTime fecha_inicio);
}
