<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@ include file="header.jsp" %>
<div class="page-action">Home</div>

<c:if test="${isItemOutOfStock}">
    <div id="out-of-stock" class="add-to-cart-failure page-action">
        Sorry, that item is temporarily out of stock. Please check back later!
    </div>
</c:if>
<c:if test="${hasItemBeenAdded}">
    <div id="added-to-cart" class="add-to-cart-success page-action">
        Awesome, you added <b><c:out value="${addedItemName}" /></b> to your cart!

        You can keep shopping, or <a href="/cart">view my cart</a>.
    </div>
</c:if>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Type</th>
        <th>Quantity</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}" varStatus="row">
        <tr>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.type}"/></td>
            <td><c:out value="${item.quantity}"/></td>
            <td>

                    <form:form action="/" method="post" modelAttribute="item">
                        <form:hidden path="itemId" value="${item.itemId}"/>
                        <button class="reserve-button add-to-cart" type="submit">
                            Add to cart
                        </button>
                    </form:form>

                    <%--<form:form action="cart/skipCart" method="post" modelAttribute="item">--%>
                        <%--<form:hidden path="itemId" value="${item.itemId}"/>--%>
                        <%--<button class="reserve-button" type="submit" name="reserve" id="reserve" value="Reserve Item">--%>
                            <%--Purchase Item--%>
                        <%--</button>--%>
                    <%--</form:form>--%>

            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="footer.jsp" %>