package com.example.demo;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
 * 프레임 워크, 더이상은 req, res에 의존하지 말자. 상속x.
 * 
 * */

import com.google.gson.Gson;

@RestController /*@RestController => json을 지원한다.*/
public class RestDeptController /*extends가 없음.*/{
	Logger logger = LogManager.getLogger(RestDeptController.class);
	@Autowired /*묶어주는 역할.*/
	DeptLogic deptLogic = null;
	@RequestMapping("dept/responsePage")
	public String responsePage() {
		logger.info("responsePage 호출 성공");
		return "Korean Page";
	}
	
	@RequestMapping("dept/jsonDeptList")
	public String jsonDeptList() {
		logger.info("jasonDeptList 호출 성공");
		List<Map<String,Object>> deptList = null;
		deptList = deptLogic.jsonDeptList();
		Gson g = new Gson();
		String imsi = g.toJson(deptList);
		return imsi;
	}
}
