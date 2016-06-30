<%--
  Created by IntelliJ IDEA.
  User: maha
  Date: 29/06/16
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>signup</title>
    <%--<link rel="stylesheet" type="text/css" href="Style.css">--%>
</head>
<body>
<h3 style="margin-left: 620px">
    Sign Up
</h3>
<form method= "post" action="/SignUp">
    <div name="signup" >
        User name:
        <input name="userName-SignUp"><br>
        Password:
        <input type="password" name="psw-SignUp"><br>
        Verify Password:
        <input type="password" name="psw-SignUp-verification"><br>
        <input type="radio" name="radio" value="agreed"> agreed<br>
        <input type="radio" name="radio" value="disagreed"> disagreed<br>
        <input type="submit" name="submit-SignUp">
    </div>
</form>
</body>
</html>
