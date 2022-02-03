package com.springboot.curso.contactos.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.curso.contactos.model.Contacto;
import com.springboot.curso.contactos.repository.ContactoRepository;

@Service
@Transactional
public class ContactoServiceImpl  implements IContactoService{

	
	@Autowired 
	private ContactoRepository contactoRepository;
	  
	
	@Override
	public Iterable<Contacto> findAll() {
		
		return  contactoRepository.findAll();
	}

	@Override
	public void save(Contacto contacto) {
			
		contactoRepository.save(contacto);
		
	}

	@Override
	public void update(Contacto contacto) {
		
		contactoRepository.save(contacto);
		
	}

	@Override
	public Optional<Contacto> findById(Integer id) {
		
		return   contactoRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		
		contactoRepository.deleteById(id);
		
	}

	
	
}
