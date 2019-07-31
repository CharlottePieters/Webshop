<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Update Product</title>
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
        <h2>Update Product</h2>
    </header>

    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>


        <form method="post" action="Controller?action=updateProductToDB&productId=${product.productId}" novalidate="novalidate">
            <p><label for="name">Name</label><input type="text" id="name" name="name" required value="<c:out value='${product.name}'/>"></p>
            <p><label for="description">Description</label><input type="text" id="description" name="description" required value="<c:out value='${product.description}'/>"></p>
            <p><label for="price">Price</label><input type="text" id="price" name="price" required value="<c:out value='${product.price}'/>"></p>
            <p><input type="submit" id="updateProduct" value="Update Product"></p>
        </form>
    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>

</div>
</body>
</html>
