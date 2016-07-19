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
    <p align="center">£<c:out value="${grandTotal}" /></p>
</div>
<div class="cardEntry">
    <h3 id="details">Credit Card Details</h3>
    <form id="credit_card_form" action="/gateway" method="post" onsubmit="return validateCreditCardDetails()">

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
                <span class="text-error">Must enter a card number.</span>
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
                <select id="date_month" name="expiry_month">
                    <option value="NONE">Month</option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
                <select id="date_year" name="expiry_year">
                    <option value="NONE">Year</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                    <option value="2026">2026</option>
                    <option value="2027">2027</option>
                </select>
            </div>
            <span class="text-error">Must enter an expiration date.</span>
        </div>

        <input type="hidden" name="amount" value=${grandTotal}>

        <div id="cancel" class="controls">
            <button id="cancelButton" onclick="location.href = '/';">Cancel</button>
        </div>

        <div id="submit" class="controls">
            <button type="submit" id="makePayment" value="Submit" onclick="return showCardErrorMessage()">Make Payment
            </button>
        </div>


    </form>

</div>
</body>
</html>

