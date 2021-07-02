<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Dom Out</title>
</head>
<body>
	<div id="d_out"></div>
	
	<script>
		window.onload = function(){
			let output = 
				`<ul>
					<li>Java</li>
					<li>Java Script</li>
					<li>React</li>
				</ul>`
		const d_out = document.querySelector('#d_out');
		d_out.innerHTML = output;
		}
	</script>
</body>
</html>