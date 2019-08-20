<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>My Cart</title>
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
                <li id="actual"><a href="Controller?action=cart">My Cart</a></li>
            </ul>
        </nav>
        <h2>My Cart</h2>
    </header>

    <main>
        <table>
            <tr>
                <th>Amount</th>
                <th>Name</th>
                <th>Price</th>
            </tr>
            <c:forEach var="entry" items="${cart}">
                <tr>
                    <td>${entry.value}</td>
                    <td>${entry.key.name}</td>
                    <td>&euro; ${entry.key.price * entry.value}</td>
                </tr>
            </c:forEach>
            <caption>My Cart</caption>
        </table>
    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>

</html>