<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Deleting one book</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>You have just deleted one book</h1>
				<p>This one:</p>
			</div>
		</div>
	</section>
	<section class="container">
			<p>Title: ${book.title}<p/>
			<p>Authors: ${book.authors}<p/>
			<p>Status: ${book.status}<p/>
	</section>
	<section class="container">
		<h3>Now you can see all books (or do something else)</h3>
		<a href="all">See all books</a><br>
		<a href="/webstore">Do something else</a>
	</section>
	<section class="container">
		<a href="<c:url value="/webstore/j_spring_security_logout" />" > Logout</a><br>
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>
