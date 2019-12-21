package com.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.crm.entity.Status;
import com.crm.repository.StatusRepository;

@Controller
@RequestMapping("status")
public class StatusController {
	@Autowired
	StatusRepository statusRepository;
	
	@GetMapping("")
	public  Object GetAll() {
		List<Status> status = statusRepository.findAll();
		if (status == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Status>>(status, HttpStatus.OK);
	}
}
