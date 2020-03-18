<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<title>Registration</title>
</head>
<body>
<c:out value="${message}"></c:out>
<form action="Controller" method="post">
<input type="hidden" name="command" value="registration">
<input type="text" name="name" value="" placeholder="name">
<input type="text" name="login" value="" placeholder="login">
<input type=text name="password" value="" placeholder="password">
<input type="submit" value="Registration">
</form>
<a href="index.jsp">Авторизация</a>
</body>
</html>