package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.crm.dto.TaskStatusDTO;
import com.crm.repository.TaskRepository;

@Controller
public class HomeController {

	@Autowired
	TaskRepository taskRepository;

	@GetMapping("")
	public String index(Model model) {
		List<TaskStatusDTO> results = taskRepository.findTaskStatusDTO();
		int sum = 0;

		for (TaskStatusDTO item : results) {
			if (item.getStatusId().equals("7282820f-7e28-4a15-b71d-f1950e6e9df7")) {
				model.addAttribute("countNonProcess", item.getCount());
				sum += item.getCount();
			} else if (item.getStatusId().equals("cc913d9c-b53e-46a7-b1f1-2697ab181aa1")) {
				model.addAttribute("countProcess", item.getCount());
				sum += item.getCount();
			} else if (item.getStatusId().equals("237ed7bb-2f1d-4315-9c7f-6c4a65ed5206")) {
				model.addAttribute("countSuccess", item.getCount());
				sum += item.getCount();
			}

		}

		model.addAttribute("sumTask", sum);

		model.addAttribute("count_task", results);

		return "home/index";
	}
}
