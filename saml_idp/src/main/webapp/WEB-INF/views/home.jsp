<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SAML Login Example</title>
</head>
<body>
	<h1>IdP</h1>
	<h3>UUID = ${uuid}</h3>
	<a href="/saml_idp/logout"><button>로그아웃</button></a>
	<br>
	<br>
	<a href="http://localhost:8080/saml"><button>SP로 이동</button></a>
</body>
</html>