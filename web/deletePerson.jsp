<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Delete Person</title>
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
                <li><a href="Controller?action=cart">My Cart</a></li>
            </ul>
        </nav>
        <h2>Delete Person</h2>
    </header>

    <main>
        <h3>Are you sure you want to delete ${person.firstName} ${person.lastName}?</h3>
        <form method="post" action="Controller?action=deletePersonFromDB&userid=${person.userid}" novalidate="novalidate">
            <p><input type="submit" id="deletePerson" value="Yes"></p>
        </form>
        <form method="post" action="Controller?action=users">
            <p><input type="submit" id="noDeletePerson" value="No"></p>
        </form>
    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>

</div>
</body>
</html>
