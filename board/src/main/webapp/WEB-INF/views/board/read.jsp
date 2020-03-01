<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board Write</title>
</head>
<script ></script>
<body>
	<div class="continer">
	BOARD READ PAGE
		<table>
			<tbody>
				<tr>
					<td>
						<label for="subject">제목</label>
					</td>
					<td>
						<div>${board.title}</div>
					</td>
				</tr>
				<tr>
					<td>
						<label for="rgstDate">작성일</label>
					</td>
					<td>
						<div>${board.rgstDate}</div>
					</td>
				</tr>
				<tr>
					<td>
						<label for="content">내용</label>
					</td>
					<td>
						<div>
							${board.content}
						</div>
					</td>
				</tr>
			</tbody>			
		</table>
		<div id="fileDiv">
			<p>첨부파일</p>
			<c:forEach items="${board.boardFiles}" var="file" varStatus="status">
				<span>${file.fileName}  (${file.fileSize})</span>
				<span><a href="${pageContext.request.contextPath}/board/fileDown?fileId=${file.fileId}">내려받기</a></span>
			</c:forEach>
		</div>
		<div id="buttonDiv">
			<button onclick="location.href=${pageContext.request.contextPath}/board/update?id=${board.id}">수정</button>
			<button onclick="location.href=${pageContext.request.contextPath}/">목록</button>
		</div>
	</div>
</body>
</html>