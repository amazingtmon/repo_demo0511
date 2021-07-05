<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Document</title>
    <style>
      * {
        border: 1px solid black;
      }

      h1 {
        border: 1px solid orangered;
      }
    </style>
  </head>

  <body>
    <script>
      function pClick(event) {
        event.preventDefault();
        console.log("p tag");
      }

      function h1Here() {
        console.log("h1");
      }

      function divHere() {
        console.log("div");
      }
    </script>

    <div>
      <div onclick="divHere()">
        <h1 onclick="h1Here()">
          <p onclick="pClick(event)">p tag</p>
        </h1>
      </div>
    </div>
  </body>
</html>
