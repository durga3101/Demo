<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: luke
  Date: 7/6/16
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title id="shopping-cart">Shopping Cart</title>

</head>
<body>
<p>Shopping Cart</p>

${item}
${item.description}
${item.itemId}
${item.name}
${item.price}
${item.quantity}

<form:form action="reserve" method="post" modelAttribute="item">
    <form:hidden path="itemId" value="${item.itemId}"/>
    <button class="reserve-button" type="submit" name="reserve" id="reserve" value="Reserve Item">
        Reserve Item
    </button>
</form:form>

</body>
</html>
