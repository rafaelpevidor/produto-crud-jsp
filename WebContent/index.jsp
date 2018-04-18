<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.: Stock :.</title>
</head>
<body>
	<% 
        System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/login"); 
    %>
</body>
</html>