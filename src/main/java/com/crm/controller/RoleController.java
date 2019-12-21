package com.crm.controller;

import java.util.UUID;

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

import com.crm.entity.Role;
import com.crm.repository.RoleRepository;
 

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	
	
	@GetMapping("")
	public String index(Model model) {	
		model.addAttribute("roles",roleRepository.findAll());
		 
		return "role/index";
	}
	
	@GetMapping("/add")
	public String add(Model model) {	
		model.addAttribute("role", new Role());
		return "role/add";
	}
	
	@PostMapping("/add")
	public String add(@Valid @ModelAttribute Role role, BindingResult erros) {
		
		
		role.setId(UUID.randomUUID().toString());
	
		roleRepository.save(role);
		return "redirect:/role";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable String id) {	
		model.addAttribute("role", roleRepository.findById(id));
		return "role/edit";
	}
	
	@PostMapping("edit")
	public String edit(@Valid @ModelAttribute Role role, BindingResult erros) {	
		roleRepository.save(role);
		return "redirect:/role";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@Valid @ModelAttribute Role role, BindingResult erros, @PathVariable String id) {	
		roleRepository.deleteById(id);
		return "redirect:/role";
	}
		
}
