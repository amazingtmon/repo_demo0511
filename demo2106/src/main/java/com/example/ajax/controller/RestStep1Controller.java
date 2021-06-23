package com.example.ajax.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajax.vo.MemberVO;

@RestController
@RequestMapping("/step1")
public class RestStep1Controller {
	@GetMapping("/get/{id}")
	public String get(@PathVariable Long id, @RequestParam String name) {
		System.out.println("call Success");
		System.out.println("id: "+id+"name: "+name);
		
		return "id: "+id+"name: "+name;
	}
	
	@PostMapping("/post")
	public MemberVO post(@RequestBody MemberVO mvo) {
		System.out.println("post call Success");
		System.out.println("MemberVo: "+mvo);
		
		return mvo;
	}
	
	@DeleteMapping("/delete")
	public void delete() throws InterruptedException{
		Thread.sleep(1000*2);
		
	}
	
	@PutMapping("/put")
	public MemberVO put(@RequestBody MemberVO mvo) {
		System.out.println("put call Success");
		System.out.println("MemberVo: "+mvo);
		
		return mvo;
	}
}
