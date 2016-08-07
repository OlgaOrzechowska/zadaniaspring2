<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Access denied</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>This is a 403 page</h1>
				<p>You have no access</p>
				<c:if test="${not empty userName}">
					<p>As a user: ${userName} you cannot do what you want.</p>
				</c:if>
				<c:if test="${empty userName}">
					<p>You have to log in to do what you want.</p>
				</c:if>
			</div>
		</div>
	</section>
	<section class="container">
		<a href="<c:url value="/webstore/j_spring_security_logout" />" > Logout</a><br>
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>
