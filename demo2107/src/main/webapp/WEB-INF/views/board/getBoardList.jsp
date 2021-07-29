<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%
	List<Map<String,Object>> boardList = null;
	boardList = (List<Map<String,Object>>)request.getAttribute("boardList");
	int size = 0;
	if(boardList != null){
		size = boardList.size();
		out.print("List size: "+boardList.size());
	}
%>    
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Board - [WEB-INF]</title>
        <%-- <jsp:include page="../../../common/commonUIglobal.jsp" flush="false" /> --%>
        <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
        <script type="text/javascript" src="../easyui/jquery.min.js"></script>
        <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript">
            function search() {
                $('#dg_datagrid').datagrid({
                    url: 'jsonGetBoardList.sp4',
                });
            }
            
            function ins() {
                console.log("입력창 호출");
                $('#dlg_ins').dialog('open');
            }
            
            function insAction(){
                console.log("입력액션 호출");
                $('#board_ins').submit();
            }
           
            function boardSel(){
            	console.log("boardSel!!");
                $('#dg_datagrid').datagrid({
                    url: 'getBoardDetail.sp4',
                    onLoadSuccess: function(){
                    },
                });
            }
            
 			function selected(){
 				$('#dg_datagrid').datagrid({
 					onClickRow: function(index,row){
 						console.log(`${index} : ${row}`);
 						$(this).datagrid('beginEdit', index);
 						var ed = $(this).datagrid('getEditor', {index:index,field:field});
 						$(ed.target).focus();
 					},
 				});
 			}          
            
            document.write("<% out.print("In script: "+size); %>");
            
        </script>
    </head>

    <body>
        <script type="text/javascript">
            $(document).ready(function(){
            	$('#dg_datagrid').datagrid({
					onDblClickRow: function(index,row){
						console.log(index+', '
								+row["BM_NO"]+', '
								+row["BM_WRITER"]);
                    	url: 'getBoardDetail.sp4';
					},
                    /* columns:[[
                    	{field:'BM_NO', title:'no', width:50, align:'center'}.
                    ]] */
                });
                $('#btn_sel').bind('click', function(){
                    boardSel();
                });
                $('#btn_ins').bind('click', function(){
                    boardIns();
                });
                $('#btn_upd').bind('click', function(){
                    boardUpd();
                });
                $('#btn_del').bind('click', function(){
                    boardDel();
                });
            });
        </script>    
        <table id="dg_datagrid" class="easyui-datagrid" data-options="title:'게시판 ', toolbar:'#tb_board'" 
        		style="width: 900px">
            <thead>
                <tr>
                    <th data-options="field:'BM_NO'" style="width: 50px" align="center">no</th>
                    <th data-options="field:'BM_WRITER'" style="width: 100px">writer</th>
                    <th data-options="field:'BM_TITLE'" style="width: 100px">title</th>
                    <th data-options="field:'BM_CONTENT'" style="width: 300px">content</th>
                    <th data-options="field:'BM_DATE'" style="width: 100px">date</th>
                    <th data-options="field:'BM_HIT'" style="width: 50px">hit</th>
                    <th data-options="field:'BS_FILE'" style="width: 100px">file</th>
                </tr>
            </thead>
            <tbody>
<%
// 조회 결과가 없을시
if(size == 0){
%> 
			    <tr>
			    	<th colspan="7">조회결과가 없습니다.</th>
			    </tr>         
<%
}
//조회 결과가 있을 때.
else {
	for(int i=0; i<size; i++){
		Map<String,Object> rmap = boardList.get(i);
		if(i==size) break;
%>
			    <tr>
			    	<td><%=rmap.get("BM_NO")%></td>
			    	<td>
<!-- 너 댓글이니? -->       
<%
	String imgPath = "\\board\\";
	if(Integer.parseInt(rmap.get("BM_POS").toString()) >0 ){
		for(int j=0;j<Integer.parseInt(rmap.get("BM_POS").toString());j++){
			out.print("&nbsp;&nbsp;");
		}
%>
	<!-- 여기는 html 땅이다.  -->
	<img src="<%=imgPath %>reply.gif" border="0">
<%
	}/////////////////end of if
%>	   			    	
			    	<%=rmap.get("BM_WRITER")%></td>
					<td><a href="getBoardDetail.sp4?bm_no=<%=rmap.get("BM_NO")%>">
							<%=rmap.get("BM_TITLE")%></a></td>
			    	<td><%=rmap.get("BM_CONTENT")%></td>
			    	<td><%=rmap.get("BM_DATE")%></td>
			    	<td><%=rmap.get("BM_HIT")%></td>
			    	<td>
<%
	if(rmap.get("BS_FILE")==null || rmap.get("BS_FILE").toString().length()==8){
%>
			    	<%="파일없음"%>
<%
	}
	else{
%>			    			
			    	<a href="download.jsp?bs_file=<%=rmap.get("BS_FILE") %>" style="text-decoration:none;">
			    			<%=rmap.get("BS_FILE") %></a>
<%
	}
%>			    			
			    	</td>
			    </tr> 
<%
	}
}
	%> 
            </tbody>
            <div id="tb_board" style="padding:2px 5px;">
                <a id="btn_sel" href="#" class="easyui-linkbutton" text="조회" iconCls="icon-search"
                    plain="true"></a>
                <a id="btn_ins" href="javascript:ins()" class="easyui-linkbutton" text="입력" iconCls="icon-add" plain="true"></a>
                <a id="btn_upd" href="#" class="easyui-linkbutton" text="수정" iconCls="icon-edit" plain="true"></a>
                <a id="btn_del" href="#" class="easyui-linkbutton" text="삭제" iconCls="icon-cancel" plain="true"></a>
            </div>

        </table>
    <!--=========================== [[글쓰기 화면 시작]] =============================-->
    <div id="dlg_ins" class="easyui-dialog" title="글쓰기" data-options="iconCls:'icon-save', closed:true, footer:'#ft_ins'" style="width:600px;height:650px;padding:10px">
    	<form id="board_ins" method="post" enctype="multipart/form-data" action="boardInsert.sp4">    
    	<input type="hidden" name="bm_pos" value="0">
    	<input type="hidden" name="bm_step" value="0">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="bm_title" label="제목:" labelPosition="top" data-options="prompt:'제목'" style="width:400px;">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox"  name="bm_writer" label="작성자:" labelPosition="top" data-options="prompt:'작성자'" style="width:250px;">
        </div>        
        <div style="margin-bottom:20px">
            <input class="easyui-textbox"  name="bm_content" label="내용:" labelPosition="top" data-options="prompt:'내용',multiline:true, width:500, height:120">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="bm_email" label="Email:" labelPosition="top" data-options="prompt:'Enter a email address...',validType:'email'" style="width:100%;">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox"  name="bm_pw" label="비밀번호:" labelPosition="top" style="width:200;">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-filebox" label="첨부파일:"  name="bs_file" labelPosition="top" data-options="width:'400px'" >
        </div>
    	</form>
    </div>    
    <div id="ft_ins">
		<a href="javascript:insAction()" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">저장</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">취소</a>
	</div>
    <!--=========================== [[글쓰기 화면   끝 ]] =============================-->          
    </body>
    </html>