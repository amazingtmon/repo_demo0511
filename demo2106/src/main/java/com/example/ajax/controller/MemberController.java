package com.example.ajax.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberLogic memberLogic = null;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest req, @RequestParam Map<String, Object> pmap) {
		HttpSession session = req.getSession();
		String s_name = null;
		s_name = memberLogic.login(pmap);
		session.setAttribute("s_name", s_name);
		
		return "redirect:loginResult.jsp";
	}
}
