<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Adding books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<h1>You can add a book here</h1>
		</div>
	</section>	
	<section>
			<form:form method="post" modelAttribute="newBook">	
				<p>Authors: <form:input class="form-control" path="authors" type="text" /></p>
				<p>Title: <form:input class="form-control" path="title" type="text" /></p>
				<p><input class="btn btn-lg btn-success btn-block" type="submit" value="Submit" />
				<input class="btn btn-lg btn-success btn-block" type="reset" value="Reset" /></p>
			</form:form>
	</section>
	<section class="container">
		<a href="<c:url value="/webstore/j_spring_security_logout" />" > Logout</a><br>
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>
