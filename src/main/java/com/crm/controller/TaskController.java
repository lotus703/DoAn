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

import com.crm.entity.Task;
import com.crm.repository.GroupRepository;
import com.crm.repository.StatusRepository;
import com.crm.repository.TaskRepository;
import com.crm.repository.UserRepository;

@Controller
@RequestMapping("task")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("tasks", taskRepository.findTaskDTO());
		return "task/index";
	}
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("user", userRepository.findAll());
		model.addAttribute("group", groupRepository.findAll());
		model.addAttribute("status", statusRepository.findAll());
		return "task/add";
	}
	@PostMapping("/add")
	public String add(@Valid @ModelAttribute Task task, BindingResult erros) {
		
		// Tạo id cho task
		task.setId(UUID.randomUUID().toString());
	
		taskRepository.save(task);
		return "redirect:/task";
	}
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable String id) {
		model.addAttribute("task", taskRepository.findById(id));
		model.addAttribute("group",groupRepository.findAll());
		model.addAttribute("user", userRepository.findAll());
		model.addAttribute("status",  statusRepository.findAll());
		return "task/edit";
	}
	@PostMapping("edit")
	public String edit(@Valid @ModelAttribute Task task, BindingResult erros) {
		taskRepository.save(task);
		return "redirect:/task";
	}
	@GetMapping("delete/{id}")
	public String delete(@Valid @ModelAttribute Task task, BindingResult erros, @PathVariable String id) {
		taskRepository.deleteById(id);
		return "redirect:/task";
	}
	 
	
}
