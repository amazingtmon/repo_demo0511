<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Data Parsing</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript">
    	function imgAreaError(){
    		$("#imgViewArea").css({'display': 'none'});$
    	}
    
    </script>
  </head>
  <body>
    [[parsing test]]
    <script type="text/javascript">
         let urlApi =
           "http://book.interpark.com/api/search.api?key=A0C5D742FAE5C3A0D18BD4DFE8E00E336F4873DCF0A18D39C2C5A29920C4C91D&query=안드로이드&output=json";

         $.ajax({
           type: "get",
           url: urlApi,
           dataType: "json",
           success: function (result) {
             let imsi = JSON.stringify(result);
             let parse = JSON.parse(imsi).item;
               let htmlTag ='<table id="tableObj" cols="4" border="1" width="900">';
             for (i = 0; i < parse.length; i++) {
               htmlTag+='<tr>';
               htmlTag+='<td rowspan="2" bgcolor="pink" width="130" height="150" align="center">';
               htmlTag+='<img id="imgViewArea" src="'+parse[i].coverLargeUrl+'" alt="" style="width: 120px; height: 140px" />';
               htmlTag+='</td>';
               htmlTag+='<td id="td11">"'+parse[i].title+'"</td>';
               htmlTag+='</tr>';
               htmlTag+='<tr style="width: 100%px; height: 150px">';
               htmlTag+='<td id="td21" colspan="3" bgcolor="yellow">"'+parse[i].description+'"</td>';
             }///////////////end of for
               htmlTag+='</tr>';
               $("#d_result").html(htmlTag);
           },
           error: function (xhrObject) {
             $("#d_result").text(xhrObject.responseText);
           },
         });
    </script>
    <div id="d_result"></div>
  </body>
</html>
