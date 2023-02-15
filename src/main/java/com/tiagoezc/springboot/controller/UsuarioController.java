package com.tiagoezc.springboot.controller;

import java.util.Optional;

import javax.validation.Valid;

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
	public String listarUsuario(Model model, @ModelAttribute("mensagem") String mensagem) {
		
		model.addAttribute("usuarios", usuarioRepository.findAll());
		model.addAttribute("mensagem", mensagem);
		return "/auth/admin/admin-listar-usuario";
	}
	
	@GetMapping("/admin/apagar/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
		Usuario usuario = usuarioRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
		
		usuarioRepository.delete(usuario);
		
		attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		return "redirect:/usuario/admin/listar";
		
	}
	
	@GetMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Long id, Model model) {
		Optional<Usuario> usuarioVelho = usuarioRepository.findById(id); // evitar ponteiro nulo
		if (!usuarioVelho.isPresent()) {
			throw new IllegalArgumentException("Usuário inválido: " + id);
		}
		
		Usuario usuario = usuarioVelho.get();
		model.addAttribute("usuario", usuario);
		
		return "/auth/user/user-alterar-usuario";
		
	}
	
	@PostMapping("/editar/{id}")
	public String editarUsuario(@PathVariable("id") Long id, @Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			usuario.setId(id);
			return "/auth/user/user-alterar-usuario";
		}
		
		usuarioRepository.save(usuario);
		redirectAttributes.addFlashAttribute("mensagem", "Editado com sucesso");
		return "redirect:/usuario/admin/listar";
		
	}
	
}



