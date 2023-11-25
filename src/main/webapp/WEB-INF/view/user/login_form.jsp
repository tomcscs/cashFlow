<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/resource/style/style.css">
<title>로그인 - CashFlow</title>
</head>
<body>
	<div class="container">
		<div class="align-right py-3">
			<a href="${pageContext.servletContext.contextPath }/user/login">로그인</a> /
			<a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a>
		</div>
		<div class="px-5 py-2 align-center mt-6">
			<h3>CashFlow는 소비 관리를 쉽고 효율적으로 도와주는 플랫폼입니다.</h3>
			<p class="xxlarge-font">캐쉬플로우에 로그인</p>
			<div>
				<form class="login-form align-left" action="${pageContext.servletContext.contextPath }/user/login/handle" method="post">
					<c:if test="${error }">
						<div class="red-white px-1">아이디 또는 비밀번호가 일치하지 않습니다.</div>
					</c:if>
					<div class="my-5">
						<label class="w100 bold">계정 아이디(*)</label>
						<input type="text" class="large-font w100" name="loginId"/>
					</div>
					<div class="my-5">
						<label class="w100 bold">계정 비밀번호(*)</label>
						<input type="password" class="large-font w100" name="loginPassword"/>
					</div>
					<div class="my-5">
						<input type="checkbox" class="large-font" name="keep" />
						<label>로그인 상태 유지</label>
					</div>
					<div>
						<button class="py-3 black-white large-font w100">로 그 인</button>
					</div>
				</form>
				<div class="my-3">
					캐쉬플로우에 처음이신가요?
					<a href="${pageContext.servletContext.contextPath }/user/join">가입하기</a>
				</div>
			</div>
		</div>

	</div>
</body>
</html>
