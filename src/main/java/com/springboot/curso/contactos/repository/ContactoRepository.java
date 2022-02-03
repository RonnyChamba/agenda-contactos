package com.springboot.curso.contactos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.curso.contactos.model.Contacto;

@Repository
public interface ContactoRepository  extends JpaRepository<Contacto, Integer>{

}
