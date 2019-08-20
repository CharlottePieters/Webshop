<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div id="container">
		<header>
			<h1>
				<span>Web shop</span>
			</h1>
			<nav>
				<ul>
					<li id="actual"><a href="Controller">Home</a></li>
					<li><a href="Controller?action=users">Users</a></li>
					<li><a href="Controller?action=products">Products</a></li>
					<li><a href="Controller?action=addProduct">Add Product</a></li>
					<li><a href="Controller?action=signUp">Sign up</a></li>
					<li><a href="Controller?action=cart">My Cart</a></li>
				</ul>
			</nav>
			<h2>Home</h2>

		</header>
		<main>
			<p>
				Sed ut perspiciatis unde omnis iste natus error sit
				voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
				ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
				dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
				aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
				qui ratione voluptatem sequi nesciunt.
			</p>

			<div id="quoteForm">
				<form method="post" action="Controller?action=quote">
					<p>Do you want to see a quote?</p>
					<p>
						<input type="radio" name="showQuote" value="yes" <c:if test="${cookie.quote.value eq 'yes'}">checked</c:if>> Yes
						<input type="radio" name="showQuote" value="no" <c:if test="${cookie.quote.value eq 'no'}">checked</c:if>> No
					</p>
					<p><input type="submit" id="sendQuote" value="Send"></p>
				</form>
			</div>

			<div id="quote">
				<c:if test="${cookie.quote.value eq 'yes'}">
					<p>Even a dead fish can go with the flow.</p>
				</c:if>
			</div>

			<c:choose>
				<c:when test="${sessionScope.user == null}">
					<c:if test="${not empty error}">
						<div class="alert-danger">
							<p>${error}</p>
						</div>
					</c:if>
					<div id="loginForm">
						<form method="post" action="Controller?action=authenticate">
							<p><label for="userId">User ID:</label><input type="text" name="userId" id="userId" required></p>
							<p><label for="password">Password:</label><input type="password" name="password" id="password" required></p>
							<p><input type="submit" id="login" value="Log In"></p>
						</form>
					</div>
				</c:when>
				<c:otherwise>
					<h3>Welcome, ${sessionScope.user.firstName}!</h3>
					<div id="logoutForm">
						<form method="post" action="Controller?action=logout">
							<p><input type="submit" id="logout" value="Log Out"></p>
						</form>
					</div>
				</c:otherwise>
			</c:choose>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>