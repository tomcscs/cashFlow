<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/resource/style/style.css">
<title>지출내역등록 - CashFlow</title>
</head>
<body>
	<div class="align-right py-3 black-white"
		style="display: flex; justify-content: space-between; align-items: center">
		<div class="px-3">
			<a href="${pageContext.servletContext.contextPath }/index">#</a>
		</div>
		<div style="display: flex; align-items: center; gap: 4px" class="px-3">
			<div>
				<img
					src="${pageContext.servletContext.contextPath }${sessionScope.logonUser.avatar.imageUrl }"
					style="width: 36px;" />
			</div>
			<div>
				<b>${sessionScope.logonUser.nickname }</b>님 로그온 ||
				<a
			href="${pageContext.servletContext.contextPath }/user//handle"
			style="padding-right: 4px">로그아웃</a>
			</div>
		</div>
	</div>
	<div
		style="display: flex; align-items: center; gap: 4px; justify-content: flex-end;">
		<a
			href="${pageContext.servletContext.contextPath }/private/spend/log"
			style="padding-right: 4px">지출내역 관리</a>
	</div>
	<div class="container">
		<div class="px-5 py-2 align-center mt-6">
			<h3>지출내역을 작성하세요.</h3>
			<p class="xxlarge-font">상세 작성시 분석에 도움이 되요.</p>
			<div>
				<form class="login-form align-left" method="post"
					action="${pageContext.servletContext.contextPath }/private/spend/write">
					<div class="my-2">
						<label class="w100 bold" for="spendAt">날짜(*)</label> <input
							type="date" class="large-font w100" name="spendAt" id="spendAt"
							value="${now }" max="${now }" />
					</div>
					<div class="my-2">
						<label class="w100 bold">카테고리(*)</label> <select name="categoryId"
							class="large-font w100">
							<c:forEach var="one" items="${categories }">
								<option value="${one.id }">${one.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="my-2">
						<label class="w100 bold" for="amt">지출금액(*)</label> <input
							type="number" class="large-font w100" name="amt" id="amt"
							step="1000" />
					</div>
					<div class="my-2">
						<label class="w100 bold" for="useDesc">사용내역(*)</label> <input
							type="text" class="large-font w100" name="useDesc" id="useDesc" />
					</div>
					<div class="my-5">
						<button class="py-3 black-white large-font w100">지출내역기입</button>
					</div>
					<c:if test="${param.error eq true}">
						<div class="red-white px-1 align-center">
							데이터 등록에 문제가 발생하였습니다.<br /> 다시 시도하세요.
						</div>
					</c:if>
				</form>
			</div>
		</div>

	</div>
</body>
</html>
