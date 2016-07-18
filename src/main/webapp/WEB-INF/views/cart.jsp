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
<c:choose>
    <c:when test="${isCartEmpty}">
        <div id="empty-cart" class="empty-cart page-action">
            <p>Oops, looks like your cart is empty. <b><a href="/">Add items</a></b></p>
            <img src="http://fe867b.medialib.glogster.com/media/a4/a4ef95c9cd527f82a9008124821afed35bc57f7ccdf8373d54a52be381ffb48b/empty.jpg"/>
        </div>

    </c:when>
    <c:otherwise>
        <table class="items-table">
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
                <c:set var="item" value="${entry.key}"/>

                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${entry.value}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td>
                </tr>
            </c:forEach>
        </table>

        <%--<div id ="tax">--%>
        <%--VAT: <div id="vat"> ${vat}</div>--%>
        <%--<br>--%>
        <%--Duty: <div>${duty}</div>--%>
        <%--</div>--%>
        <table id="tax">
            <tr>
                <td>Sub Total:</td>
                <td>${subTotal}</td>
            </tr>
            <tr>
                <td >Vat (${vatRate}%):</td>
                <td>${vat}</td>
            </tr>
            <tr>
                <td>Duty (${dutyRate}%):</td>
                <td>${duty}</td>
            </tr>
            <tr>
                <td>Grand Total:</td>
                <td id="grand_total">${grandTotal}</td>
            </tr>
        </table>
        <br>
        <br>
        <div id="shopping-actions">
            <form:form action="/" method="get">
                <button type="submit" id="cancel">
                    Continue Shopping
                </button>
            </form:form>

            <form:form action="/shippingAddress" method="get" modelAttribute="item">
                <form:hidden path="itemId" value="${item.itemId}"/>
                <button type="submit" name="checkout" id="checkout" value="Reserve Item">
                    Proceed to Checkout
                </button>
            </form:form>
        </div>

    </c:otherwise>
</c:choose>

</body>
</html>
