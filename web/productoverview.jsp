<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Products</title>
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
                <li id="actual"><a href="Controller?action=products">Products</a></li>
                <c:if test="${sessionScope.user.role eq 'admin'}"><li><a href="Controller?action=addProduct">Add Product</a></li></c:if>
                <li><a href="Controller?action=signUp">Sign up</a></li>
                <li><a href="Controller?action=cart">My Cart</a></li>
            </ul>
        </nav>
        <h2>Product Overview</h2>
    </header>

    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Add to cart</th>
            </tr>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><a href="Controller?action=updateProduct&productId=${product.productId}">${product.name}</a></td>
                    <td>${product.description}</td>
                    <td>${product.price}</td>
                    <td><a href="Controller?action=deleteProduct&productId=${product.productId}">Delete</a></td>
                    <td>
                        <form style="display: inline" method="post" action="Controller?action=addToCart&productId=${product.productId}">
                            <input type="number" id="amount" name="amount" value="1" width="3" required >
                            <input type="submit" id="addToCart" value="Add to Cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <caption>Products Overview</caption>
        </table>
    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>

</html>