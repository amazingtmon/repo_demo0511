package com.example.ajax.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLogic {
	
	@Autowired
	private MemberDao memberDao = null;
	
	public String login(Map<String,Object> pmap) {
		String s_name = null;
		s_name = memberDao.login(pmap);
		return s_name;
	}
}
