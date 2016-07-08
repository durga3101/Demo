<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="<c:url value='/scripts/css/payment.css' />" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/scripts/css/main.css' />" type="text/css"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<h2 align="center">Payment Details</h2>
<div class="summary">
    <h3 align="center">Order Summary</h3>
    <p align="center">£${totalAmount}
</div>
<div class="cardEntry">
    <h3>Credit Card Details</h3>
    <form id="credit_card_form" action="/payment/gateway" method="post" onsubmit="return validateCreditCardDetails();">

        <div id="card_type_field">
            <label for="card_type">Card type</label>
            <div class="controls">
                <select id="card_type" name="country">
                    <option value="NONE">Select a card</option>
                    <option value="VISA">Visa</option>
                    <option value="AMEX">American Express</option>
                    <option value="DISCOVER">Discover</option>
                    <option value="MASTERCARD">MasterCard</option>
                </select>
                <span class="text-error">Must select a card type!</span>
            </div>
        </div>

        <div id="card_number_field">
            <label for="card_number">Card Number</label>
            <div class="controls">
                <input type="text" id="card_number" placeholder="xxxx-xxxx-xxxx-xxxx" name="card_number">
                <span class="text-error">Must enter a card number</span>
            </div>
        </div>

        <div id="ccv_field">
            <label for="card_ccv">CCV</label>
            <div class="controls">
                <input type="password" id="card_ccv" name="card_ccv">
                <span class="text-error">Must enter a ccv!</span>
            </div>
        </div>

        <div id="date_field">
            <input type="month" name="date_field">
        </div>

        <div class="controls">
            <button type="submit" id="makePayment" value="Submit" onclick="return showCardErrorMessage()">Make Payment
            </button>
        </div>


</form>

</div>
</body>
</html>

<script type="text/javascript" src="<c:url value='/scripts/js/card_validator.js' />"></script>