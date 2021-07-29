<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String bm_no = request.getParameter("bm_no");
	String bs_file = request.getParameter("bs_file");
	String bm_writer = request.getParameter("bm_writer");
	String bm_email = request.getParameter("bm_email");
	out.print(bm_no+", "+bm_writer+", "+bm_email+", "+bs_file);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateForm.sp4 - [WEB-INF]</title>
<link rel="stylesheet" type="text/css"
	href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<form id="dlg_upd" method="post" action="boardUpdate.sp4">
		<input type="hidden" name="bm_pos" value="0"> 
		<input type="hidden" name="bm_step" value="0">
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="bm_title" label="제목:"
				labelPosition="top" data-options="prompt:'제목'" style="width: 400px;">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="bm_writer" label="작성자:"
				labelPosition="top" data-options="prompt:'작성자', value:'<%=bm_writer%>'"
				style="width: 250px;">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="bm_content" label="내용:"
				labelPosition="top"
				data-options="prompt:'내용',multiline:true, width:500, height:120">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="bm_email" label="Email:"
				labelPosition="top"
				data-options="prompt:'Enter a email address...',value:'<%=bm_email%>'"
				style="width: 100%;">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-textbox" name="bm_pw" label="비밀번호:"
				labelPosition="top" style="width: 200;">
		</div>
		<div style="margin-bottom: 20px">
			<input class="easyui-filebox" label="첨부파일:" name="bs_file"
				labelPosition="top" data-options="width:'400px'">
		</div>
	</form>
    <div id="ft_ins">
		<a href="javascript:updAction()" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">수정</a>
		<a href='javascript:$("#dlg_upd").dialog("close");' class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">닫기</a>
	</div>	
</body>
</html>