<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Users</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
<div id="container">
    <header>
        <h1><span>Web shop</span></h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li id="actual"><a href="Controller?action=users">Users</a></li>
                <li><a href="Controller?action=products">Products</a></li>
                <c:if test="${sessionScope.user.role eq 'admin'}"><li><a href="Controller?action=addProduct">Add Product</a></li></c:if>
                <li><a href="Controller?action=signUp">Sign up</a></li>
                <li><a href="Controller?action=cart">My Cart</a></li>
            </ul>
        </nav>
        <h2>User Overview</h2>
    </header>

    <main>
        <aside>
            <form action="Controller?action=sortUsers" method="post">
                <select name="sortStyle">
                    <option value="email" selected>Email</option>
                    <option value="firstName" <c:if test="${cookie.sortStyle.value eq 'firstName'}">selected</c:if>>First Name</option>
                    <option value="lastName"<c:if test="${cookie.sortStyle.value eq 'lastName'}">selected</c:if>>Name</option>
                </select>
                <input type="submit" value="Submit Query"/>
            </form>

        </aside>
        <table>
            <tr>
                <th>User Id</th>
                <th>E-mail</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
                <th>Check Password</th>
            </tr>
            <c:forEach var="person" items="${persons}">
                <tr>
                    <td>${person.userid}</td>
                    <td>${person.email}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td><a href="Controller?action=deletePerson&userid=${person.userid}">Delete</a></td>
                    <td><a href="Controller?action=checkPasswordPage&userid=${person.userid}">Check</a></td>
                </tr>
            </c:forEach>
            <caption>Users Overview</caption>
        </table>
    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>

</html>