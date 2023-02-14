package com.tiagoezc.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tiagoezc.springboot.model.Usuario;
import com.tiagoezc.springboot.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/novo")
	public String novoUsuario(Model model) {
		
		model.addAttribute("usuario", new Usuario());
		return "/publica-criar-usuario";
		
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
	
		if (result.hasErrors()) {
			return "/publica-criar-usuario";
		}
		
		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Salvo com sucesso");
		return "redirect:/usuario/novo";
		
	}
	
	
	@GetMapping("/admin/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "/auth/admin/admin-listar-usuario";
	}
	
}



