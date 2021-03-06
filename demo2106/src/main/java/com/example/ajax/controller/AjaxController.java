package com.example.ajax.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

@Controller
@RequestMapping("/ajax80")
public class AjaxController {
	@GetMapping("/pizza/getCustomerList")
	public String getCustomerList(@RequestParam Map<String, Object> pMap, Model model) {
		System.out.println("pMap: " + pMap);
		List<Map<String, Object>> cusList = new ArrayList<>();
		int i = 0;

		Map<String, Object> cmap = new HashMap<>();
		cmap.put("mem_name", "john");
		cmap.put("mem_addr", "서울시 강남구 청담동");
		cmap.put("mem_tel", "0240001234");
		cusList.add(i++, cmap);
		cmap = null;

		cmap = new HashMap<>();
		cmap.put("mem_name", "selly");
		cmap.put("mem_addr", "서울시 노원구 상계동");
		cmap.put("mem_tel", "0240005678");
		cusList.add(i++, cmap);
		cmap = null;

		cmap = new HashMap<>();
		cmap.put("mem_name", "noha");
		cmap.put("mem_addr", "서울시 성북구 길음동");
		cmap.put("mem_tel", "0240001357");
		cusList.add(i++, cmap);

		model.addAttribute("cusList", cusList);
		return "forward:/ajax80/pizza/jsonCustomer.jsp";
	}

	@PostMapping("/pizza/orderPizza")
	public String orderConfirm(@RequestParam Map<String, Object> pMap, Model model) {
		System.out.println("pMap: " + pMap);
		return "redirect:/ajax80/pizza/orderConfirm.jsp";
	}

	@GetMapping("/login/getLogin")
	public String getLogin(@RequestParam Map<String, Object> pMap, Model model) {
		List<Map<String, Object>> memList = new ArrayList<>();

		Map<String, Object> mMap = new HashMap();
		mMap.put("mem_id", "apple");
		mMap.put("mem_pw", "123");
		memList.add(mMap);

		String p_name = (String) pMap.get("usrname");
		String p_pw = (String) pMap.get("psw");
		System.out.println(p_name + ", " + p_pw);

		model.addAttribute("memList",memList);
		
		if (p_name.equals(mMap.get("mem_id"))) {
			System.out.println("1");
			return "forward:/ajax80/login/loginConfirm.jsp";
		} else {
			System.out.println("2");
			return "forward:/ajax80/login/loginFail.jsp";
		}
		
		// return "forward:/ajax80/login/loginConfirm.jsp";
	}
}
