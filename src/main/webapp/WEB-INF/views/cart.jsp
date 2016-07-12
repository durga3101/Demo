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


<p id="item_name"><c:out value="${item.name}"/></p>
<p id="item_description"><c:out value="${item.description}"/></p>
<p id="item_price"><c:out value="${item.price}"/></p>
<%--<p id="item_cod"><c:out value="${item.description}"/></p>--%>

<form:form action="/" method="get">
    <button type="submit" id="cancel">
        Cancel
    </button>
</form:form>

<form:form action="/payment" method="get" modelAttribute="item">
    <form:hidden path="itemId" value="${item.itemId}"/>
    <button class="checkout-button" type="submit" name="checkout" id="checkout" value="Reserve Item">
        Check Out
    </button>
</form:form>

</body>
</html>
