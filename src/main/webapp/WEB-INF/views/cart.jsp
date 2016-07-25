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
            <p>Oops, looks like your cart is empty. <b><a id="add-items" href="/">Add items</a></b></p>
        </div>

    </c:when>
    <c:otherwise>
        <table class="items-table">
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>Unit Price</th>
                <th>Total Price</th>
                <th></th>
            </tr>
            </thead>

            <c:forEach var="entry" items="${items}" varStatus="row">
                <c:set var="item" value="${entry.key}"/>

                <tr>
                    <td><img  height="80px" width="100px" src="<c:out value="${item.imageURL}"/>"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${entry.value}"/></td>
                    <td>£<c:out value="${item.price}"/></td>
                    <td>£<c:out value="${item.price * entry.value}"/></td>
                    <td>
                        <form:form action="/cart" method="post" modelAttribute="item">
                            <form:hidden path="itemId" value="${item.itemId}"/>
                            <button type="submit" id="remove${item.name}">
                                Remove from cart
                            </button>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <table id="tax">
            <tr>
                <td>Sub Total:</td>
                <td>£${subTotal}</td>
            </tr>
            <tr>
                <td >Vat (${vatRate}%):</td>
                <td>£${vat}</td>
            </tr>
            <tr>
                <td>Duty (${dutyRate}%):</td>
                <td>£${duty}</td>
            </tr>
            <tr>
                <td>Grand Total:</td>
                <td id="grand_total">£${grandTotal}</td>
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
                <form:hidden value="${item.itemId}"  path="itemId"/>
                <button type="submit" name="checkout" id="checkout" value="Reserve Item">
                    Proceed to Checkout
                </button>
            </form:form>
        </div>

    </c:otherwise>
</c:choose>

</body>
</html>
