<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>One book</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Here you can see one book</h1>
				<p>It's the book you wanted to see</p>
			</div>
		</div>
	</section>
	<section class="container">
		<table style="width:100%">
			<tr>
				<td>Title</td>
				<td>${book.title}</td>
			</tr>
			<tr>
				<td>Authors</td>
				<td>${book.authors}</td>
			</tr>
			<tr>
				<td>Status</td>
				<td>${book.status}</td>
			</tr>
		</table>
	</section>
	<section class="container">
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>
