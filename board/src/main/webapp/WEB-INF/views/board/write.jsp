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
<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function(){
// 	document.querySelector('#fileDel').onclick = function () {
// 		alert('I\'m clicked!');
// 	};
});
</script>
<body>
	<div class="continer">
	BOARD WRITE PAGE
	<section id="container">
		<form role="form" method="post" action="${pageContext.request.contextPath }/board/save" enctype="multipart/form-data">
			<table>
				<tbody>
					<tr>
						<td>
							<label for="title">제목</label><input type="text" id="title" name="title" />
						</td>
					</tr>	
					<tr>
						<td>
							<label for="content">내용</label><textarea id="content" name="content" ></textarea>
						</td>
					</tr>
				</tbody>			
			</table>
			<div>
				<input type="file" name="file">	
			</div>
			<div>
				<button type="submit">작성</button>
				<button onclick="location.href=${pageContext.request.contextPath}/">목록</button>
			</div>
		</form>
	</section>
	</div>
</body>
</html>