<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="https://www.jeasyui.com/easyui/demo/demo.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="https://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
	<style>
		#d_search {
			position: absolute;
		}
		
		.listIn {
			background: #CCFFFF;
			width: 165px;
		}

		.listOut {
			background: #FFFFFF;
		}		
		
	</style>
	<script type="text/javascript">
	function choCheck(){
		let chos = [
			"ㄱ","ㄲ","ㄴ","ㄷ","ㄸ",
			"ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ",
			"ㅇ","ㅈ","ㅉ","ㅊ","ㅋ",
			"ㅌ","ㅍ","ㅎ"
			];
		console.log("양".charCodeAt(0));
	}
	//choCheck();
	
	function choHangul(str){
		//사용자로부터 입력받은 한글을 출력해본다.
		console.log(str);
		//초성을 열거하기
		let chos = [
			"ㄱ","ㄲ","ㄴ","ㄷ","ㄸ",
			"ㄹ","ㅁ","ㅂ","ㅃ","ㅅ","ㅆ",
			"ㅇ","ㅈ","ㅉ","ㅊ","ㅋ",
			"ㅌ","ㅍ","ㅎ"
			];
		let result = '';
		//사용자가 입력한 글자가 오라클일 경우 length는 3
		for(let i = 0; i<str.length; i++){
			const code = str.charCodeAt(i) - 44032;
			
			if(code > -1 && code< 11172){
				result += chos[Math.floor(code/588)];
			}///// end of if
 		}//////// end of for
		return result;
	}/////////// end of choHangul
	
	function clearMethod(){
		$("#d_search").css("backgroundColor", "#FFFFFF");
		$("#d_search").css("border", "none");
		$("#d_search").html("");
	}
	</script>
</head>
<body>
<input id="book_title2" style="width:100px;"/>
<input id="book_title" class="easyui-textbox" style="width:200px;" />
<div id="d_search"></div>

<script type="text/javascript">
	$(document).ready(function(){
		let t = $("#book_title");
		console.log($("#book_title"));
		t.textbox('textbox').bind('keydown', function(e){
			console.log(e);
			if(e.keyCode == 13){
				console.log('Enter Event');
			}
		})
		t.textbox('textbox').bind('keyup', function(e){
			let vm_word = $("#_easyui_textbox_input1").val().toUpperCase();
			let choKeyword = choHangul($("#_easyui_textbox_input1").val());
			if(vm_word != '' && choKeyword == ''){
				choKeyword = vm_word;
				choMode = 'Y';
			} else {
				choKeyword = vm_word;
				choMode = 'N';
			}
			console.log('choMode: '+choMode+', choKeyword: '+choKeyword);
			let p_word = $("#_easyui_textbox_input1").val();
			let param = "b_name="+p_word+"&choMode="choMode;
			console.log(param);
		})
		
		$.ajax({
			type:'POST',
			url:'/htmlBookList.jsp',
			data: param,
			success: function(result){
				console.log(result);
				$('#d_search').css('border','#000000 1px solid');
				$('#d_search').css('backgroundColor','#FFFFFF');
				$('#d_search').html(result);
			}
		})
	})
</script>

</body>
</html>