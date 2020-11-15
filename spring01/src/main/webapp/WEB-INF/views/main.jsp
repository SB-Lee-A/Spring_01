<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="include/menu.jsp"/> 
<!-- include 태그를 이용해서 디렉토리에있는 jsp를 불러옴-->
<h2>name : ${name}</h2>
<h2>message : ${message}</h2>

</body>
</html>