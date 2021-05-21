package com.example.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ZipCodeDao {
	Logger logger = LogManager.getLogger(ZipCodeDao.class);
	private static final String NAMESPACE = "com.example.demo.";
	
	@Autowired(required=false)
	private SqlSession sqlSession = null;
	public List<Map<String, Object>> getZipCodeList() {
		logger.info("getZipCodeList called");
		List<Map<String,Object>> zipList = null;
		zipList = sqlSession.selectList(NAMESPACE+"entireList");
		return zipList;
	}
}
