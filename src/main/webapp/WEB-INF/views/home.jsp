<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-action">Home</div>

<c:if test="${isItemOutOfStock}">
    <div id="out-of-stock" class="add-to-cart-failure page-action">
        Sorry, that item is temporarily out of stock. Please check back later!
    </div>
</c:if>
<c:if test="${hasItemBeenAdded}">
    <div id="added-to-cart" class="add-to-cart-success page-action">
        Awesome, you added <b><c:out value="${addedItemName}"/></b> to your cart!

        You can keep shopping, or <a href="/cart">view my cart</a>.
    </div>
</c:if>
<div>
    <table id="home-table">
        <thead>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Price (£)</th>
            <th>Description</th>
            <th>Type</th>
            <th></th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${items}" varStatus="row">
            <tr>
                <td style="width: 12%"><img width="100%" height="same-as-width" src="<c:out value="${item.imageURL}"/>"/></td>
                <td style="width: 25%;"><c:out value="${item.name}"/></td>
                <td style="width: 10%;"><c:out value="${item.price}"/></td>
                <td style="width: 33%;"><c:out value="${item.description}"/></td>
                <td style="width: 10%;"><c:out value="${item.type}"/></td>
                <td style="width: 10%;">
                    <form:form action="/" method="post" modelAttribute="item">
                        <form:hidden path="itemId" value="${item.itemId}"/>
                        <button class="reserve-button add-to-cart" id="add_item${item.name}" type="submit">
                            Add to cart
                        </button>
                    </form:form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="footer.jsp" %>