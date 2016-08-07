<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Finding books</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<h1>You can find a book here</h1>
		</div>
	</section>	
	<section>
		<form action="/webstore/books/books-by-many">
			<fieldset>
                <div class="form-group">
					<input class="form-control" placeholder="Title" name='title' type="text">
 				</div>
				<div class="form-group">
					<input class="form-control" placeholder="Author" name='author' type="text" value="">
				</div>
				<input class="btn btn-lg btn-success btn-block" type="submit" value="Submit">
			</fieldset>
    	</form>
	</section>
	<section class="container">
		<a href="<c:url value="/webstore/j_spring_security_logout" />" > Logout</a><br>
		<a href="/webstore">Back to webstore</a>
	</section>
</body>
</html>
