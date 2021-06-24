<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.*" %>      
<%
	List<Map<String,Object>> cusList = null;
	cusList = (List)request.getAttribute("cusList");
	Gson gson = new Gson();
	String test = gson.toJson(cusList);
	out.print(test);
%>
<div>
	<h1>力格</h1>
	<span>力格捞 公均捞衬</span>
	<h2><%=test%></h2>
</div>