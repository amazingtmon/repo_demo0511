package com.example.ajax.controller;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	private static final String NAMESPACE = "com.example.ajax.";
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate = null;
	
	public String login(Map<String, Object> pmap) {
		String s_name = null;
		pmap.put("mem_id", "pumpkin");
		pmap.put("mem_pw", "123");
		sqlSessionTemplate.selectOne(NAMESPACE+"proc_login", pmap);
		System.out.println("msg: "+pmap.get("mem_id"));
		
		return s_name;
	}

}
