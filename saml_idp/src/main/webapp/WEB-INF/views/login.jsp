<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SAML Login Example</title>

</head>
<body>
	<h1>로그인(IdP)</h1>
	<form action="" method="post">
		<input type="text" name="id">
		<input type="text" name="password">
		<input type="submit" value="LOGIN">
	</form>
	<br>
	<a href="http://localhost:8080/saml"><button>SP로 이동</button></a>
</body>
</html>