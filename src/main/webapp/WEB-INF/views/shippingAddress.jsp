<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="shippingAddress"/>

<%@ include file="header.jsp" %>

<div class="page-action">
    Shipping Address
</div>

<c:if test="${not empty validationMessage.errors}">
    <div id="resultsMessage" class="page-action error">
        There were errors. Please check your input.
    </div>
</c:if>

<form id="shippingAddress_form" action="/shippingAddress/addShippingAddress" method="post" onsubmit="return  validateShippingAddressForm();">
    <div id="street1_field">
        <label for="fld_street1">Street 1</label>
        <div class="controls">
            <input type="text" id="fld_street1" placeholder="Street" name="street1">
            <span class="text-error">Must Enter Street Details</span>
        </div>
    </div>
    <div id="street2_field">
        <label for="fld_street2">Street 2</label>
        <div class="controls">
            <input type="text" id="fld_street2" placeholder="Street" name="street2">
        </div>
    </div>

    <div id="city_field">
        <label for="fld_city">City</label>
        <div class="controls">
            <input type="text" id="fld_city" placeholder="city" name="city">
            <span class="text-error">Must Enter City</span>
        </div>
    </div>

    <div id="state_field">
        <label for="fld_state">State/Province</label>
        <div class="controls">
            <input type="text" id="fld_state" placeholder="Your state/province" name="state">
            <span class="text-error">Must Enter State/Province</span>
        </div>
    </div>

    <div id="postcode_field">
        <label for="fld_postcode">Postal Code</label>
        <div class="controls">
            <input type="text" id="fld_postcode" placeholder="555-123456" name="postcode">
            <span class="text-error">Must Enter Post Code</span>
        </div>
    </div>
    <br>
    <div>

        <div class="controls">
            <button type="submit" id="Proceed to Payment" value="Submit" onclick="showShippingAddressErrorMessage()">Proceed to Payment</button>
        </div>
            <%--<form:form action="/shippingAddress" method="get" modelAttribute="item">--%>
                <%--<form:hidden path="itemId" value="${item.itemId}"/>--%>
                <%--<button class="checkout-button" type="submit" name="checkout" id="checkout" value="Reserve Item">--%>
                    <%--Proceed to Checkout--%>
                <%--</button>--%>
            <%--</form:form>--%>
    </div>

</form>

<%@ include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value='/scripts/js/shippingAddress.js' />"></script>
