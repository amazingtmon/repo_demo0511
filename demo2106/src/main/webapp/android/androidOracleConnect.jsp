<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//jsp쓰고 html이라고 읽는다. -> View 계층
//jsp에서 로직을 구현한다. -> 야근
//jsp쓰고 json을 내보낸다. -> 클라우드기반 서비스
//POJO -> HashMapBinder hmd = new HashMapBinder(req);
//Spring -> @RequestParam Map<String, Object>

	String user_id = request.getParameter("mem_id");
	String user_pw = request.getParameter("mem_pw");
	out.print(user_id+", "+user_pw);
	out.print("<br>");
	out.print("success#message");
%>