<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	String s_name = (String)session.getAttribute("s_name");
	out.print("session: "+s_name);
%>