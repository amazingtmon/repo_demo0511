package com.example.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/ajax80")
public class RestAjaxController {
	//@GetMapping(value="/pizza/jsonGetCustomerList", prodduces="application/json;charset=UTF-8")
	@GetMapping("/pizza/jsonGetCustomerList")
	public String jsonGetCustomerList(@RequestParam Map<String,Object> pMap) {
		System.out.println("pMap: "+pMap);
		List<Map<String,Object>> cusList = new ArrayList<>();
		Map<String,Object> cmap = new HashMap<>();
		cmap.put("mem_name", "john");
		cmap.put("mem_addr", "서울시 강남구 청담동");
		cmap.put("mem_tel", "0240001234");
		cusList.add(cmap);
		cmap = new HashMap<>();
		cmap.put("mem_name", "selly");
		cmap.put("mem_addr", "서울시 노원구 상계동");
		cmap.put("mem_tel", "0240005678");
		cusList.add(cmap);
		cmap = new HashMap<>();
		cmap.put("mem_name", "noha");
		cmap.put("mem_addr", "서울시 성북구 길음동");
		cmap.put("mem_tel", "0240001357");
		cusList.add(cmap);
		Gson gson = new Gson();
		String text = gson.toJson(cusList);
		
		return text;
	}
}
