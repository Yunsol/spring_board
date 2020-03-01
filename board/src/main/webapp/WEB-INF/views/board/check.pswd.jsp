<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%
	Boolean wrongPswd = (Boolean) request.getAttribute("wrongPswd");
	String nextPage = (String) request.getAttribute("nextPage");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board Check Password</title>
</head>
<script type="text/javascript">
	var wrongPswd = <%=wrongPswd%>;
	if (wrongPswd) {
		alert('비밀번호가 틀렸습니다. 다시 입력 웅앵 ');
	}
</script>
<body>
	<div class="continer">
	BOARD CHECK PASSWORD
	<section id="container">
		<form role="form" method="post" action="${pageContext.request.contextPath }/board/<%=nextPage%>">
			<input type="hidden" name="id" value="${board.id}"/>
			<div>
				<label>글 비밀번호</label>
				<input type="password" name="pswd"/>
			</div>
			<div>
				<button type="submit">확인</button>
				<a href="${pageContext.request.contextPath}/">목록</button>
			</div>
		</form>
	</section>
	</div>
</body>
</html>