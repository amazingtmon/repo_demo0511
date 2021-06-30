package com.example.ajax.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajax.vo.MemberVO;
import com.google.gson.Gson;

@RestController
@RequestMapping("/login")
public class RestMemberController {
	Logger logger = LogManager.getLogger(RestMemberController.class);
	@Autowired
	private MemberLogic memberLogic = null;
	
	@RequestMapping("/jsonLogin")
	public String jsonLogin(HttpServletRequest req, @RequestParam Map<String, Object> pmap) {
		HttpSession session = req.getSession();
		String s_name = null;
		s_name = memberLogic.login(pmap);
		session.setAttribute("s_name", s_name);
		
		List<MemberVO> list = new ArrayList<>();
		MemberVO mvo = new MemberVO();
		mvo.setMem_name(s_name);
		mvo.setMem_id(pmap.get("mem_id").toString());
		
		Gson gson = new Gson();
		String imsi = gson.toJson(list);
		return imsi;
	}
	
	@PostMapping("/cookieLogin")
	public String cookieLogin(HttpServletResponse res, 
			@RequestParam Map<String, Object> pmap) {
		logger.info("cookieLogin");
		
		String s_name = null;
		s_name = memberLogic.login(pmap);
		Cookie cname = new Cookie("cname", s_name);
		cname.setMaxAge(60*2);
		cname.setPath("/");
		res.addCookie(cname);
		
		return s_name;
	}
}
