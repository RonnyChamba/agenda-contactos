package com.springboot.curso.contactos.service;

import java.util.Optional;

import com.springboot.curso.contactos.model.Contacto;

public interface IContactoService {


	Iterable<Contacto> findAll();
	
	void save(Contacto contacto);
	
	void update(Contacto contacto);
	
	Optional<Contacto> findById(Integer id);
	
	void delete(Integer id);
}
