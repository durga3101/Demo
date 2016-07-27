<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="shippingAddress"/>

<%@ include file="header.jsp" %>

<div class="page-action">
    Enter your shipping address
</div>


<c:if test="${not empty validationMessage.errors}">
    <div id="resultsMessage" class="page-action error">
        There were errors. Please check your input.
    </div>
</c:if>

<form id="shippingAddress_form" action="/shippingAddress/addShippingAddress" model="account" method="post"
      onsubmit="return  validateShippingAddressForm();">
    <div id="street1_field">
        <label for="fld_street1">Street 1</label>
        <div class="controls">
            <input type="text" id="fld_street1" placeholder="Sesame St" name="street1">
            <span class="text-error"></span>
        </div>
    </div>
    <div id="street2_field">
        <label for="fld_street2">Street 2 <sup>*</sup></label>
        <div class="controls">
            <input type="text" id="fld_street2" placeholder="Street line 2" name="street2">
            <span class="text-error"></span>
        </div>
    </div>

    <div id="city_field">
        <label for="fld_city">City</label>
        <div class="controls">
            <input type="text" id="fld_city" placeholder="San Francisco" name="city">
            <span class="text-error"></span>
        </div>
    </div>

    <div id="state_field">
        <label for="fld_state">State/Province <sup>*</sup></label>
        <div class="controls">
            <input type="text" id="fld_state" placeholder="California" name="state">
            <span class="text-error"></span>
        </div>
    </div>

    <div id="postcode_field">
        <label for="fld_postcode">Postal Code</label>
        <div class="controls">
            <input type="text" id="fld_postcode" placeholder="555-123456" name="postcode">
            <span class="text-error"></span>
        </div>
    </div>
    <div id="country_field">
        <label for="fld_country">Country</label>
        <div id="fld_country" class="controls">

            <input type="text" readonly="readonly" id="fld_country_2" name="country" value=" ${country} ">
        </div>
    </div>

    <%--<br>--%>
    <p><sup>*</sup>&nbsp;<small>Optional Field</small></p>

    <div class="controls">
        <a href="/cart">
            <button type="button" id="back_to_cart">
                Back to Cart
            </button>
        </a>
        <button type="submit" id="proceedToPayment" value="Submit" onclick="showShippingAddressErrorMessage()">
            Proceed to Payment
        </button>
    </div>

</form>


<%@ include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value='/scripts/js/shippingAddress.js' />"></script>
