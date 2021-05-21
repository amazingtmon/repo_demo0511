<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map"  %>
<%
	List<Map<String,Object>> zipList = 
			(List<Map<String,Object>>)request.getAttribute("zipList");
	int size = 0;
	if(zipList != null){
		size = zipList.size();
	}
	out.print("zipList의 크기 : "+size);
	out.print("<br/>");
	out.print("value: "+zipList);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ZipCodeList</title>
</head>
<body>
	ZipCodeList page loaded!!
</body>
</html>