<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board Write</title>
</head><%
	pageContext.setAttribute("newLine", "\n");
%><body>
	<div class="continer">
	BOARD WRITE PAGE
	<a href="${pageContext.request.contextPath }/">목록으로</a>
	<section id="container">
		<form role="form" method="post" action="${pageContext.request.contextPath }/board/save">
			<table>
				<tbody>
					<tr>
						<td>
							<label for="subject">제목</label><input type="text" id="title" name="title" />
						</td>
					</tr>	
					<tr>
						<td>
							<label for="content">내용</label><textarea id="content" name="content" ></textarea>
						</td>
					</tr>
					<tr>
						<td>						
							<button type="submit">작성</button>
						</td>
					</tr>			
				</tbody>			
			</table>
		</form>
	</section>
	</div>
</body>
</html>