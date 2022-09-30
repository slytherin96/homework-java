<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
</head>

<body>
<h1>List products</h1>


<ul>
    <c:forEach var="item" items="${products.products}">
        <font size="4" color="black" face="Arial"><li>${item.id} ${item.title} ${item.cost}</li>
    </c:forEach>
</ul>

</body>
</html>