<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cookie cs[] = request.getCookies();
	String c_name = null;
	for(int i=0; i<cs.length;i++){
		if("c_name".equals(cs[i].getName())){
			c_name = cs[i].getValue();
		}
	}
	//c_name = "name";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Map Service</title>
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/demo/demo.css">
<script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>	
<!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4pd9w90oFRuCZOILRd07MBwVAthwIbIA&callback=initMap&region=
kr" async></script> -->
<script>
	let c_name;
	function logIn(){
		console.log('login');
		let u_id = $("#mem_id").val();
		let u_pw = $("#mem_pw").val();
		let param = "mem_id="+u_id+"&mem_pw="+u_pw;
		$.ajax({
			type:"post",
			data:param,
			url:"/login/cookieLogin",
			success:function(data){
				console.log('success');
				c_name = data;
				$("#d_login").hide();
				$("#d_logout").show();
				$("#loginOk").text(c_name+" hi!!");
			},
			error:function(xhrObject){
				alert(xhrObject.responseText);
			}
		});
	}
	
	function logOut(){
		$.ajax({
			type:"get",
			url:"./cookieDelete.jsp?cname=cname&timestamp="+new Date.getTime(),
			success:function(data){
			$("#d_logout").hide();
			$("#d_login").show();
			},
			error:function(xhrObject){
				alert(xhrObject.responseText);
			}
		});
	}
	
	function like(u_num){
		
	}
	
	function hate(u_num){
		
	}
</script>
</head>
<body>
	<script>
/* 		$(document).ready(function(){
			let map = new google.maps.Map(document.getElementById('d_map'), {
					zoom: 14,
					center: new google.maps.LatLng(37,126),
			})
		}) */
	</script>

	<table align="center" width="900px" height="600px" border="1">
		<tr>
			<td width="700px">
				<!-- 구글 지도 서비스 시작 -->
				<div id="d_map" style="width: 695px; height: 595px;"></div>
			</td>
<%
//쿠키가 null일때
//if(c_name == null){
%>
			<!-- [[ 로그인 화면 시작 ]] -->
			<td>
				<div id="d_login" style="height: 300px; border: 1px solid blue;">
				<form>
					<table>
						<tr>
							<td><input id="mem_id" name="mem_id" class="easyui-textbox"
								data-options="iconCls:'icon-search'" style="width: 100px">
							</td>
							<td rowspan="2"><a id="btn_login" href="javascript:logIn()">로그인</a></td>
						</tr>
						<tr>
							<td><input id="mem_pw" name="mem_pw" class="easyui-textbox"
								data-options="iconCls:'icon-search'" style="width: 100px">
							</td>
						</tr>
					</table>
				</form>
				</div> 
				</td>
				<!-- [[ 로그인 화면 끝 ]] --> 
<%
//}
//else {
%> <!-- [[ 로그아웃 화면 시작 ]] -->
				<td>
				<div id="d_logout" style="height: 300px; border: 1px solid red;">
					<form>
						<table>
							<tr>
								<td><span id="loginOk"></span></td>
								<td rowspan="2"><a id="btn_login" href="javascript:logOut()">로그아웃</a>
								</td>
							</tr>
						</table>
					</form>
				</div>
				</td> 
				<!-- [[ 로그아웃 화면 끝 ]] --> 
<%
//}
%>
		</tr>
	</table>
</body>
</html>