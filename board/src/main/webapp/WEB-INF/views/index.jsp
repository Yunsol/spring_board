<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BoardList</title>
</head>
<%
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
				<td><c:set var="fileLen" value="${fn:length(board.boardFiles)}"
						scope="session" /> <c:choose>
						<c:when test="${fileLen == 0}">
					           X
					        </c:when>
						<c:otherwise>
					           O
					         </c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<ul class="pagination">
			<%--
	        이전 버튼이 클릭가능한 조건이면, a태그를 이용해 이전 버튼이 뜨도록 하고, href로 링크를 걸되
	        아까 만든 makeQuery 메서드를 이용해서 쿼리문자열을 만들어 줍니다.
	        ?page=어쩌고&perPageNum=어쩌고 이 부분을 생성해서 넣게 되는데 단 이전 버튼을 클릭하면
	        현재 페이지가 시작페이지의 -1 이 되도록 되어야 함으로 그 부분만 신경써 주면 됩니다.
	     	--%>
	     	<c:if test="${pageMaker.prev}">
				<li>
					<a href="/${pageMaker.makeQuery(pageMaker.startPage - 1)}">[이전]</a>
				</li>
			</c:if>
			<%--
	        [1][2][3]....[10] 이 부분을 삽입하는 부분이다. jstl 이용해for문을 돌면서 startPage ~ endPage까지
	        표시해주되, a태그 눌렀을 때 이동하는 page 부분에 index 지정하는 부분을 유의깁게 보길 바란다.
	    	 --%>
	     	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="index">
				<a href="/${pageMaker.makeQuery(index)}">[${index}]</a>
			</c:forEach>
			<%--
            이전버튼과 마찬가지로 다음버튼을 끝 페이지보다1개 큰게 현재 페이지가 되도록 makeQuery에 page를 넣어줍시다.
         	--%>
			<c:if test="${pageMaker.next}">
				<li>
					<a href="/${pageMaker.makeQuery(pageMaker.endPage + 1) }">[다음]</a>
				</li>
			</c:if>
		</ul>
	</div>
</body>
</html>