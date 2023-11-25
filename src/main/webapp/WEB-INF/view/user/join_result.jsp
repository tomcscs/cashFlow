<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/resource/style/style.css">
<title>회원가입 - CashFlow</title>
</head>
<body>
	<div class="container">
		<div class="align-right py-3">
			<a href="${pageContext.servletContext.contextPath }/index">#</a>
			
		</div>
		<div class="px-5 py-2 align-center mt-6">
			<h3>CashFlow는 소비 관리를 쉽고 효율적으로 도와주는 플랫폼입니다.</h3>

			<div>
				<c:choose>
					<c:when test="${result eq 1 }">
						<p class="xxlarge-font">캐쉬플로우의 회원이 되신것을 축하드립니다.</p>
						<div></div>
					</c:when>
					<c:otherwise>
						<p class="xxlarge-font">데이터처리 과정중에 장애가 발생하였습니다.</p>
						<div>
							잠시 뒤 다시 시도해 주십시요.
							<a href="${pageContext.servletContext.contextPath }/user/join">↺</a>
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

	</div>
</body>
</html>
