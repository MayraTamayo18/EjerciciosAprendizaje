package com.shoestoreMaira.Shoestore.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoestoreMaira.Shoestore.model.venta;

@Repository
public interface Iventa extends CrudRepository<venta,String>{
    
}
