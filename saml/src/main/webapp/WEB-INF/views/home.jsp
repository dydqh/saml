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
	<h1>SAMl 로그인 예제(SP)</h1>
	<c:choose>
		<c:when test="${not empty AssertionId}">
			<h3>AssertionId = ${AssertionId}</h3>
			<h3>SP Entity ID = ${SpEntityId}</h3>
			<form action="logout">
				<button>로그아웃</button>
			</form>
		</c:when>
		<c:otherwise>
			<form action="ssoredirect">
				<button>로그인(Redirect to IdP)</button>
			</form>
			<br>
			<form action="ssoredirect2">
				<button>로그인(Local IdP)</button>
			</form>
			<br>
			<a href="http://localhost:8180/saml_idp"><button>Local IdP로 이동</button></a>
		</c:otherwise>
	</c:choose>
</body>
</html>


