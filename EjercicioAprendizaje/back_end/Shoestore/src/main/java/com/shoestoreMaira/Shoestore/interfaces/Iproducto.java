package com.shoestoreMaira.Shoestore.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shoestoreMaira.Shoestore.model.producto;

@Repository
public interface Iproducto extends CrudRepository<producto,String>{
    
}
