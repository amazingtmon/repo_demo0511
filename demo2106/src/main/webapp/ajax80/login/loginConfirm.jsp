<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.*" %>     
<%
	List<Map<String, Object>> memList = null;
	memList = (List)request.getAttribute("memList");
	
%>
        <span style="font-size: 3rem; color: white; padding: 0.5rem;">
            <span><%=memList.get(0).get("mem_id")%></span><span>님 환영합니다 : )</span>
        </span>