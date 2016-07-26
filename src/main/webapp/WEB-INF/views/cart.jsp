<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Shopping Cart"/>

<%@ include file="header.jsp" %>


<div class="page-action">Your shopping cart</div>
<c:choose>
    <c:when test="${isCartEmpty}">
        <div id="empty-cart" class="empty-cart page-action">
            <p>Looks like your cart is empty... <b><a id="add-items" href="/">click</a></b> to add items</p>
        </div>

    </c:when>
    <c:otherwise>
        <table class="items-table">
            <thead>
            <tr id="cart-tr-head">
                <th class="padded-col"></th>
                <th class="padded-col">Name</th>
                <th class="padded-col">Description</th>
                <th class="padded-col">Qty</th>
                <th class="padded-col">Unit price (&pound;)</th>
                <th class="padded-col">Total price (&pound;)</th>
                <th></th>
            </tr>
            </thead>

            <c:forEach var="entry" items="${items}" varStatus="row">
                <c:set var="item" value="${entry.key}"/>

                <tr id="cart-tr-body">
                    <td style="width: 10%" class="padded-col"><img width="100%" height="same-as-width" src="<c:out value="${item.imageURL}"/>"/></td>
                    <td style="width: 20%" class="padded-col"><c:out value="${item.name}"/></td>
                    <td style="width: 35%" class="padded-col"><c:out value="${item.description}"/></td>
                    <td style="width: 2%" class="padded-col"><c:out value="${entry.value}"/></td>
                    <td style="width: 14%" class="padded-col"><c:out value="${item.price}"/></td>
                    <td style="width: 16%" class="padded-col"><c:out value="${item.price * entry.value}"/></td>
                    <td style="width: 3%">
                        <form:form action="/cart" method="post" modelAttribute="item">
                            <form:hidden path="itemId" value="${item.itemId}"/>
                            <input title="Remove from cart" alt="Remove from cart" type="image" width="100%" height="same-as-width" src="../../images/remove-from-cart.png" value="submit" name="submit" id="remove${item.name}">
                            </input>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <hr style/>


        <div class="row">
        <div class="col-12" align="right">

        <table id="tax">
            <tr>
                <td class="right right-pad">Subtotal:</td>
                <td>&pound;${subTotal}</td>
            </tr>
            <tr>
                <td class="right right-pad">VAT (${vatRate}%):</td>
                <td>&pound;${vat}</td>
            </tr>
            <tr>
                <td class="right right-pad">Duty (${dutyRate}%):</td>
                <td>&pound;${duty}</td>
            </tr>
            <tr>
                <td class="right right-pad bold">Grand Total:</td>
                <td id="grand_total">&pound;${grandTotal}</td>
            </tr>

        </table>
        <p class="smallish italic">Taxes applicable based on country.</p>
        <br>
        <br>



        <div id="shopping-actions" align="right">
            <form:form action="/" method="get">
                <button type="submit" id="cancel">
                    Continue shopping
                </button>
            </form:form>

            <form:form action="/shippingAddress" method="get" modelAttribute="item">
                <form:hidden value="${item.itemId}"  path="itemId"/>
                <button type="submit" name="checkout" id="checkout" value="Checkout">
                    Proceed to checkout
                </button>
            </form:form>
        </div>

    </c:otherwise>
</c:choose>

</div>
</div>