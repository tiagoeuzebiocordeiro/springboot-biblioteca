package com.tiagoezc.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiagoezc.springboot.model.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping("/novo")
	public String novoUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
		
	}
	
}



