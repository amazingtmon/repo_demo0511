<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Dom Element Add</title>
</head>
<body>
	<script type="text/javascript">
	document.write("Hello");
		window.onload =  function (){
			//변수 선언
			let header = document.createElement("h1");
			let textNode = document.createTextNode("Hello DOM");
			header.appendChild(textNode);
			document.body.appendChild(header);
		}
	
	</script>
</body>
</html>