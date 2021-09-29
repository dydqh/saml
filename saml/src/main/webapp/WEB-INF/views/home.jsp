<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SAML Login Example</title>
</head>
<body>
	<h1>SAMl 로그인 예제</h1>
	<c:choose>
		<c:when test="${not empty AssertionId}">
			<h3>AssertionId = ${AssertionId}</h3>
			<form action="logout">
				<button>로그아웃</button>
			</form>
		</c:when>
		<c:otherwise>
			<form action="ssoredirect">
				<button>로그인(Redirect to IdP)</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>


