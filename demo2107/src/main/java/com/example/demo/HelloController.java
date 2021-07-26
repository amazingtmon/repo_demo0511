package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model, @RequestParam(value="name", required=false) String name ) {
		model.addAttribute("greeting", "안녕하세요." + name);
		
		return "redirect:hello.jsp";
	}
}
