<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cfn" uri="/WEB-INF/functions.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/resource/style/style.css">
<title>지출내역-cahsFlow</title>
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
				<b>${sessionScope.logonUser.nickname }</b>님 로그온 || <a
					href="${pageContext.servletContext.contextPath }/user//handle"
					style="padding-right: 4px">로그아웃</a>
			</div>
		</div>
	</div>
	<div
		style="display: flex; align-items: center; gap: 4px; justify-content: flex-end;">
		<b>지출내역</b>
		<a href="${pageContext.servletContext.contextPath }/private/spend/log"
			style="padding-right: 4px">관리</a> || 
		<a href="${pageContext.servletContext.contextPath }/private/spend/log"
			style="padding-right: 4px">분석</a>	
	</div>

	<div class="container">
		<div class="px-5 py-2 align-center mt-6">
			<h3>${sessionScope.logonUser.nickname }님의 지출내역</h3>
			<div style="width: 560px; margin: auto">
				<form
					action="${pageContext.servletContext.contextPath }/private/spend/log"
					class="my-2">
					<div
						style="display: flex; justify-content: space-between; gap: 2px; align-items: center">
						<div>
							<select name="sort">
								<c:choose>
									<c:when test="${param.sort eq 'spendAt' }">
										<option value="spendAt" selected>날짜순</option>	
									</c:when>
									<c:otherwise>
										<option value="spendAt">날짜순</option>
									</c:otherwise>
								</c:choose>
								<option value="amt" ${param.sort eq 'amt' ? 'selected' :'' }>금액순</option>
							</select>
						</div>
						<div>
							<input type="date" name="begin" value="${param.begin }"/> ~ <input
								type="date" name="end"  value="${param.end }"/>
						</div>
					</div>
					<div>
						<div
							style="padding: 4px; border-bottom: 1px solid #555; font-size: small; display: flex; flex-wrap: wrap; gap: 2px; justify-content: center">
							<c:forEach var="one" items="${cates }">
								<div style="display: flex; align-items: center">
									<input type="checkbox" name="categoryId" value="${one.id }" 
										
										${cfn:checkValue(paramValues.categoryId, one.id) ? 'checked' :'' }/>
									${one.name }
								</div>
							</c:forEach>

						</div>
						<div class="align-right">
							<button>검색</button>
						</div>
					</div>
				</form>
				<form
					action="${pageContext.servletContext.contextPath }/private/spend/delete"
					method="post">
					<table style="width: 100%">
						<tr>
							<th></th>
							<th>날짜</th>
							<th>내역</th>
							<th>금액</th>
							<th>분류</th>
						</tr>
						<c:forEach items="${logs }" var="one">
							<tr>
								<td><input type="checkbox" name="no" value="${one.no }" /></td>
								<td>${one.spendAt }</td>
								<td>${one.useDesc }</td>
								<td><fmt:formatNumber value="${one.amt }" pattern="#,###" />
								</td>
								<td>${one.category.name }</td>
							</tr>
						</c:forEach>
						<tr
							style="background-color: #444; color: #eee; font-weight: bold;">
							<td colspan="3">총 합</td>
							<td colspan="2"><fmt:formatNumber value="${total }"
									pattern="#,###" /></td>
						</tr>
					</table>
					<div class="align-right my-2"
						style="display: flex; justify-content: space-between;">
						<button type="submit" class="black-white px-2">-기록삭제</button>
						<a
							href="${pageContext.servletContext.contextPath }/private/spend/write">
							<button class="black-white px-2" type="button">+ 기록추가</button>
						</a>
					</div>

				</form>

			</div>
		</div>
	</div>

</body>
</html>
