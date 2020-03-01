<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BoardList</title>
</head><%
	pageContext.setAttribute("newLine", "\n");
%><body>
	<c:set var="count" value="${fn:length(boardList)}" />
	<a href="${pageContext.request.contextPath }/board/write">글쓰기</a>
	<table width="510" border="1">
		<tr>
			<td>글번호</td>
			<td>제목</td>
			<td>작성일시</td>
			<td>첨부여부</td>
		</tr>
		<c:forEach items="${boardList}" var="board" varStatus="status">
				<tr>
					<td>[${board.id}]</td>
					<td><a href="/board/read?id=${board.id}">${board.title}</a></td>
					<td>${board.rgstDate}</td>
					<td>
						<c:set var="fileLen" value="${fn:length(board.boardFiles)}" scope="session"/>
						<c:choose>
					        <c:when test="${fileLen == 0}">
					           X
					        </c:when>         
					        <c:otherwise>
					           O
					         </c:otherwise>
					    </c:choose>
					</td>
				</tr>
			<br>
		</c:forEach>
	</table>
</body>
</html>