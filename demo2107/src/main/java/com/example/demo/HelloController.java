package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/board")
public class HelloController {
	Logger logger = LogManager.getLogger(HelloController.class);
	private HelloLogic helloLogic = null;
	public void setHelloLogic(HelloLogic helloLogic) {
		this.helloLogic = helloLogic;
	}
	
	@GetMapping("/hello")
	public String hello(Model model, @RequestParam(value="name", required=false) String name ) {
		model.addAttribute("greeting", "안녕하세요." + name);
		
		return "hello";
	}
}
