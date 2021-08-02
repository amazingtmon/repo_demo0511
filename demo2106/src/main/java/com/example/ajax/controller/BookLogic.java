package com.example.ajax.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookLogic {
	@Autowired
	private BookDao bookDao = null;
	
	public List<Map<String, Object>> getBookList(Map<String, Object> pmap) {
		return null;
	}
	
}
