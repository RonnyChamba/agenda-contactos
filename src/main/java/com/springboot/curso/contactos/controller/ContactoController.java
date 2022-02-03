package com.springboot.curso.contactos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.curso.contactos.model.Contacto;
import com.springboot.curso.contactos.service.IContactoService;

@Controller
@RequestMapping
public class ContactoController {

	
	@Autowired
	private IContactoService contactoService;
	
	private static final Logger LOGGER= LogManager.getLogger(ContactoController.class);

	@GetMapping({"", "/"})
	public String  home(Model model) {
		
		LOGGER.info("Index agenda");
		Iterable<Contacto> contactos = contactoService.findAll();
		
		List<Contacto> listContacto = new ArrayList<>();
		
		contactos.forEach(contact ->{
			listContacto.add(contact);
		});
		System.out.println("lista: "+contactos);
		model.addAttribute("contactos", listContacto);
		return "index";
	}
	
	@GetMapping({"/nuevo"})
	public String showFormRegister(Model model) {
		
		Contacto nuevoContacto = new Contacto();	
		model.addAttribute("action", "nuevo");
		model.addAttribute("nuevoContacto", nuevoContacto);
		return "nuevo";
	}
	
	@PostMapping("/nuevo")
	public String saveContact(@Valid @ModelAttribute("nuevoContacto") Contacto nuevoContacto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model ) {
		
		LOGGER.info("Guardar usuario");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("nuevoContacto", nuevoContacto);
			
			return "nuevo";
		}
		
		// Redirect Atributes permite asignar atributos a la redireccion
		contactoService.save(nuevoContacto);
		redirectAttributes.addFlashAttribute("smsExito", "El contacto fue registrado con exito");
		return "redirect:/";
	}
	
	@GetMapping({"/editar/{id}"})
	public String showFormEdit(@PathVariable Integer id, Model model) {
		
		LOGGER.info("Id contacto a ctualizar usuario {}",id);
		Contacto contacto = null;
		Optional<Contacto> contactoEncontrado = contactoService.findById(id);
		
		contacto = contactoEncontrado.orElse(null);	
		model.addAttribute("nuevoContacto", contacto);
		model.addAttribute("action", "editar");
		return "nuevo";
	}
	
	@PostMapping("/editar/{id}")
	public String updateContact(@Valid @ModelAttribute("nuevoContacto") Contacto nuevoContacto, BindingResult bindingResult, @PathVariable Integer id, RedirectAttributes redirectAttributes, Model model ) {
		
		//LOGGER.info("Actualizar usuario: {} ", nuevoContacto );
			
		
		Contacto contacto =  contactoService.findById(id).orElse(null);
		
		contacto.setCelular(nuevoContacto.getCelular());
		contacto.setEmail(nuevoContacto.getEmail());
		contacto.setFechaNacimiento(nuevoContacto.getFechaNacimiento());
		contacto.setNombre(nuevoContacto.getNombre());
		
		System.out.println("Contacto atualizar: " + nuevoContacto);
		if (bindingResult.hasErrors()) {
			model.addAttribute("nuevoContacto", nuevoContacto);
			
			return "nuevo";
		}
		
		 // Redirect Atributes permite asignar atributos a la redireccion
		contactoService.update(contacto);
		redirectAttributes.addFlashAttribute("smsExito", "El contacto fue actualizado con exito");
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete (@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		
		Contacto contacto = contactoService.findById(id).orElse(null);
		
		if (contacto!=null) {
			
			contactoService.delete(contacto.getId());
			redirectAttributes.addFlashAttribute("smsExito", "Contacto eliminado con exito");
		}else {
			redirectAttributes.addFlashAttribute("smsExito", "No se elimino el Contacto");
		}
		
		return "redirect:/";
	}
}
