<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<c:if test="${not empty allBooks}">
					<h1>Here you can see books</h1>
				</c:if>
				<c:if test="${empty allBooks}">
					<h1>No such books</h1>
					<a href="/webstore/books/all">See all books</a>
				</c:if>
			</div>
		</div>
	</section>
	<section class="container">
		<c:if test="${not empty allBooks}">
			<table style="width:100%">
			<tr>
				<th>Title</th>
				<th>Authors</th>
				<th>Status</th>
			</tr>
				<c:forEach var="book" items="${allBooks}">
					<tr>
						<td><a href="book?id=${book.id}">${book.title}</a></td>
						<td>${book.authors}</td>
						<td>${book.status}</td>
						<td>
							<p>
								<a href="/webstore/books/delete?id=${book.id}" class="btn btn-default"> Delete this book
								</a>
							</p>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</section>
	<section class="container">
		<a href="<c:url value="/webstore/j_spring_security_logout" />" > Logout</a><br>
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>
