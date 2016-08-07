<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Added book successfully</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<h1>You have just added a book successfully</h1>
			<h3>Details of the book you've added are below:</h3>
		</div>
	</section>	
	<section>
		<div class="container">
			<p>Title: ${newBook.title}<p/>
			<p>Authors: ${newBook.authors}<p/>
			<p>Status: ${newBook.status}<p/>
   		</div>
	</section>
	<section class="container">
		<a href="<c:url value="/webstore/j_spring_security_logout" />" > Logout</a><br>
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>