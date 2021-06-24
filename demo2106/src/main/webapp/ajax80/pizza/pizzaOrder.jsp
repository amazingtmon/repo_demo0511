<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <html>

  <head>
    <meta charset="UTF-8">
    <title>Break Neck Pizza Delivery</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
      //피자 주문시 호출
      function orderPizza() {
        let paper = $("#paper").val();
        //textarea, input text 모두 디폴트가 빈문자열("")이다.
        if (paper != null && paper.length > 0) {
          $("#f_order").submit();//폼전송. -ajax제외.
        } else {
          alert("상세주문을 입력해주세요.")
          $("#f_order").focus();//커서를 이동해서 바로 입력하도록 함.    	  
          return false;
        }
      }

      //ajax 적용 구간.
      function getCustomerInfo() {
        //사용자가 입력한 전화번호.
        let user_tel = $('#mem_tel').val();
        console.log(user_tel);
        //fetch API
        $.ajax({//ajax 함수 - 여러가지 속성이 있다. type, url, dataType, success, error
          type: "get",
          url: "getCustomerList",
          dataType: "json",
          success: function (data) {//data - 분석 - {id:test}, [{id:test}], {"id":"test"} ...
            let result = JSON.stringify(data);
            console.log("result: " + result);

            let jsonDoc = JSON.parse(result);
            console.log(jsonDoc);
            let temp = '';
            if (jsonDoc.length > 0) {
              for (let i = 0; i < jsonDoc.length; i++) {
                if (user_tel === jsonDoc[i].mem_tel) {
                  temp += jsonDoc[i].mem_addr;
                }
              }
            }
            $('#mem_addr').text(temp);//html - 인터프리터를 받는다.
          },
          error: function (e) {//e는 XMLHttpRequest - 비동기 통신 객체.
            let x = e.responseXML;
            alert('fail...' + x);
          }
        });
      }
    </script>
  </head>

  <body>
    <form id="f_order" method="post" action="orderPizza">
      <input type="hidden" id="mem_name" name="mem_name" value="">
      <p><img src="breakneck-logo.gif" alt="Break Neck Pizza" /></p>
      <p>Enter your phone number:
        <input type="text" size="14" id="mem_tel" name="mem_tel" onChange="getCustomerInfo()" />
      </p>
      <p>Type your order in here:</p>
      <p><textarea name="paper" id="paper" rows="6" cols="50"></textarea></p>
      <p>Your order will be delivered to:</p>

      <p><textarea name="mem_addr" id="mem_addr" rows="4" cols="50"></textarea></p>
      <p><input type="button" id="btn_order" value="Order Pizza" onclick="orderPizza()" /></p>
    </form>
    <div id="d_msg"></div>
  </body>

  </html>