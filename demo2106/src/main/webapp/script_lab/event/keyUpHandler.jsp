<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Document</title>
  </head>
  <body>
    <script type="text/javascript">
      function whenClick(event) {
        console.log("click");
      }

      function whenKeyUp(event) {
        console.log(event);
        console.log(event.code + ", " + event.key);
      }
    </script>

    <h1 onclick="whenClick(event)">Click</h1>
    <input type="text" onkeyup="whenKeyUp(event)" />
  </body>
</html>
