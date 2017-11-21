<%--
  Created by IntelliJ IDEA.
  User: YiaKrevetko
  Date: 21.11.2017
  Time: 20:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>Index</title>
  <style>
    .main {
      background-color: rgba(0, 0, 201, 0.26);
    }
    .main h1 {
      color: white;
      margin-left: 40%;
      margin-top: 100px;
    }
    .main form {
      display: block;
      margin-top: 100px;
      margin-left: 40%;
    }
    .main form input {
      display: inline-block;
      padding-right: 10px;
      height: 50px;
      width: 80px;
      font-size: 20px;
      background-color: white;
      color: #370da9;
      border: none;
    }
  </style>
</head>
<body class="main">
<h1>Choose the parser</h1>
<form action="controller" method="post">
  <input class='button' type="submit" name="action" value="SAX"/>
  <input class='button' type="submit" name="action" value="StAX"/>
  <input class='button' type="submit" name="action" value="DOM"/>
</form>
</body>
</html>
