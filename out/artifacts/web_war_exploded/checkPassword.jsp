<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Check Password</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?action=users">Users</a></li>
                <li><a href="Controller?action=products">Products</a></li>
                <li><a href="Controller?action=addProduct">Add Product</a></li>
                <li><a href="Controller?action=signUp">Sign up</a></li>
            </ul>
        </nav>
        <h2>Check Password</h2>
    </header>

    <main>
        <c:if test="${not empty correct}">
            <c:if test="${correct}">
                <h3>The password is correct!</h3>
            </c:if>
            <c:if test="${not correct}">
                <h3>The password is incorrect.</h3>
            </c:if>
            <form method="post" action="Controller?action=users" novalidate="novalidate">
                <p><input type="submit" id="ok" value="Ok"></p>
            </form>
        </c:if>
        <c:if test="${empty correct and not empty person}">
            <h3>Fill out the password for ${person.firstName} ${person.lastName}:</h3>
            <form method="post" action="Controller?action=checkPassword&userid=${person.userid}" novalidate="novalidate">
                <p><label for="password">Password</label><input type="text" id="password" name="password"></p>
                <p><input type="submit" id="checkPassword" value="Check"></p>
            </form>
        </c:if>


    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>

</div>
</body>
</html>
