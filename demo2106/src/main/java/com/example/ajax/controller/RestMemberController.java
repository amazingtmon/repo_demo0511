package com.example.ajax.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class RestMemberController {
	
	@Autowired
	private MemberLogic memberLogic = null;
	
	@RequestMapping("/jsonLogin")
	public String jsonLogin(HttpServletRequest req, @RequestParam Map<String, Object> pmap) {
		HttpSession session = req.getSession();
		String s_name = null;
		s_name = memberLogic.login(pmap);
		session.setAttribute("s_name", s_name);
		
		return s_name;
	}
}
