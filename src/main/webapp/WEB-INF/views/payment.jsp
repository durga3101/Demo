<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="<c:url value='/scripts/css/payment.css' />" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/scripts/css/main.css' />" type="text/css"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<html>
<head>
    <title>Payment</title>

</head>
<body>

<script type="text/javascript" src="<c:url value='/scripts/js/card_validator.js' />"></script>

<h2 align="center">Payment Details</h2>
<div class="summary">
    <h3 align="center">Order Summary</h3>
    <p align="center">£${totalAmount}
</div>
<div class="cardEntry">
    <h3 id="details">Credit Card Details</h3>
    <form id="credit_card_form" action="/payment/gateway" method="post" onsubmit="return validateCreditCardDetails();">

        <div id="card_type_field">
            <label for="card_type">Card Type</label>
            <div class="controls">
                <select id="card_type" name="country">
                    <option value="NONE">Select a card</option>
                    <option value="VISA">Visa</option>
                    <option value="AMEX">American Express</option>
                    <option value="DISCOVER">Discover</option>
                    <option value="MASTERCARD">MasterCard</option>
                </select>
                <span class="text-error">Must select card type.</span>
            </div>
        </div>

        <div id="card_number_field">
            <label for="card_number">Card Number</label>
            <div class="controls">
                <input type="text" id="card_number" placeholder="xxxx-xxxx-xxxx-xxxx" name="card_number">
                <span class="text-error" >Must enter a card number.</span>
            </div>
        </div>

        <div id="card_ccv_field">
            <label for="card_ccv">CCV</label>
            <div class="controls">
                <input type="password" id="card_ccv" name="card_ccv">
                <span class="text-error">Must enter a valid CCV.</span>
            </div>
        </div>

        <div id="date_field">
            <label for="date">Expiration Date</label>
            <div id="date">
                <select id="date_month">
                    <option>Month</option>
                    <option>01</option><option>02</option><option>03</option><option>04</option>
                    <option>05</option><option>06</option><option>07</option><option>08</option>
                    <option>09</option><option>10</option><option>11</option><option>12</option>
                </select>
                <select id="date_year">
                    <option>Year</option>
                    <option>2016</option><option>2017</option><option>2018</option><option>2019</option>
                    <option>2020</option><option>2021</option><option>2022</option><option>2023</option>
                    <option>2024</option><option>2025</option><option>2026</option><option>2027</option>
                </select>
            </div>
            <span class="text-error">Must enter an expiration date.</span>
        </div>

        <div id="submit" class="controls">
            <button type="submit" id="makePayment" value="Submit" onclick="return showCardErrorMessage()">Make Payment
            </button>
        </div>

</form>

</div>
</body>
</html>

