package com.springboot.curso.contactos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@NotNull
	private String nombre;
	
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String celular;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull
	private LocalDate fechaNacimiento;
	
	private LocalDateTime fechaRegistro;
	
	@PrePersist
	public void initFechaRegistro() {
		
		fechaRegistro = LocalDateTime.now();
	}
	
}
