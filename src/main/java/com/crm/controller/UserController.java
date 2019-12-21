package com.crm.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.dto.UserDTO;
import com.crm.entity.User;
import com.crm.repository.RoleRepository;
import com.crm.repository.TaskRepository;
import com.crm.repository.UserRepository;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TaskRepository taskRepository;

	
	@GetMapping("")
	public String index(Model model) {	
		model.addAttribute("users",userRepository.findAllUserRole());	 
		return "user/index";
	}
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles", roleRepository.findAll()); 
		return "user/add";
	}
	@PostMapping("/add")
	public String add(@Valid @ModelAttribute User user, BindingResult erros) {
		
		// Tạo id cho user
		user.setId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "redirect:/user";
	}
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable String id) {
		System.out.println("ID: " + id);
		model.addAttribute("user",userRepository.findById(id));
		model.addAttribute("roles",roleRepository.findAll()); 

		return "user/edit";
	}
	@PostMapping("edit")
	public String edit(@Valid @ModelAttribute User user, BindingResult erros) {
		System.out.println("ID: " + user.getId());
		userRepository.save(user);
		return "redirect:/user";
	}
	@GetMapping("delete/{id}")
	public String delete(@Valid @ModelAttribute User user, BindingResult erros, @PathVariable String id) {
		 
		userRepository.deleteById(id);
		return "redirect:/user";
		 
		 
	}
	@GetMapping("/details/{id}")
	public String details(Model model,@PathVariable String id) {
	//User user = userRepository.findById("472557a7-4a14-49c5-b1c6-6ef3513bd867").get();
	 model.addAttribute("user", userRepository.findById(id).get());
	 model.addAttribute("num_task", taskRepository.countByUserId(id));
	 model.addAttribute("numNon", taskRepository.countByUserIdAndStatusId(id, "7282820f-7e28-4a15-b71d-f1950e6e9df7"));
	 model.addAttribute("numProcess", taskRepository.countByUserIdAndStatusId(id, "cc913d9c-b53e-46a7-b1f1-2697ab181aa1"));
	 model.addAttribute("numSuccess", taskRepository.countByUserIdAndStatusId(id, "237ed7bb-2f1d-4315-9c7f-6c4a65ed5206"));
 
	 //System.out.println(user);
		return "user/details";
	}
}
