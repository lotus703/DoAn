package com.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeniedController {
	@GetMapping("/error/403")
	public String Denied() {
		
		return "error/403";
	}
}
