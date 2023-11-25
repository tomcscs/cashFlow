<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/resource/style/style.css">
<title>효율적인 소비 - CashFlow</title>
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
	<div class="container"></div>
</body>
</html>
