<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="header.jsp" %>

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
<div class="page-action">Your Shopping Cart</div>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>Price</th>
        <th></th>
    </tr>
    </thead>

    <c:forEach var="entry" items="${items}" varStatus="row">
        <c:set var="item" value="${entry.key}" />

        <tr>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${entry.value}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td>
        </tr>
    </c:forEach>

</table>

<div style="display: flex; flex-direction: row ; justify-content: center">
    <form:form action="/" method="get">
        <button type="submit" id="cancel">
            Continue Shopping
        </button>
    </form:form>

    <form:form action="/payment" method="get" modelAttribute="item">
        <form:hidden path="itemId" value="${item.itemId}"/>
        <button class="checkout-button" type="submit" name="checkout" id="checkout" value="Reserve Item">
            Proceed to Checkout
        </button>
    </form:form>
</div>

</body>
</html>
