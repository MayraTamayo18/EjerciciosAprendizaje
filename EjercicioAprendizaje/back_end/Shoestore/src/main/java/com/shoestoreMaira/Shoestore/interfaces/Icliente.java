package com.shoestoreMaira.Shoestore.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoestoreMaira.Shoestore.model.cliente;

@Repository
public interface Icliente extends CrudRepository<cliente,String>{
    @Query("SELECT c FROM cliente c WHERE "
			
			+ "c.nombres_cliente LIKE %?1% OR "
			+ "c.apellidos_cliente LIKE %?1% OR "
			+ "c.ciudad LIKE %?1%")
			// + "c.estado LIKE %?1%")
	
	List<cliente> filtroCliente(@Param("filtroCliente")String filtro);

	@Query ("SELECT c FROM cliente c  "
					+"WHERE  c.identificacion=?1  "
			
			
			)
			List<cliente> filtroCedulaCliente(String identificacion);
    
}
