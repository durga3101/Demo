<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Invoice"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/scripts/css/main.css' />" type="text/css"/>
    <link rel="stylesheet" href="<c:url value='/scripts/css/invoice.css' />" type="text/css"/>
    <script type="text/javascript" src="<c:url value='/scripts/lib/jquery-1.10.2.js' />"></script>
    <script type="text/javascript" src="<c:url value='/scripts/lib/jquery.cookie.js' />"></script>
</head>
<body>
<section id="customer-invoice">
    <table>
        <tr>
            <td style="vertical-align: middle"><h1>Invoice</h1></td>
            <td> <img width="50%;" src="<c:url value='/images/freewheelers-logo.png' />"></td>
        </tr>
    </table>

    <br>
    <br>
    <table id="customer-details">

        <th><b>To:</b></th>
        <tr>
            <td><security:authentication property="principal.username"/></td>
            <td>Freewheelers Ltd.</td>
        </tr>
        <tr>
            <td></td>
            <td>85 Albert Embankment</td>
        </tr>
        <tr>
            <td id="customer-address1">${userDetails.street_1}</td>
            <td>London</td>
        </tr>
        <tr>
            <td id="customer-address2">${userDetails.street_2}</td>
            <td>SE1 7TP</td>
        </tr>
        <tr>
            <td id="customer-city">${userDetails.city}</td>
            <td></td>
        </tr>
        <tr>
            <td id="customer-postalCode">${userDetails.postcode}</td>
            <td>Company reg.no: 98765686</td>
        </tr>
        <tr>
            <td id="customer-someCountry">${country}</td>
            <td>EU VAT No.: GB98765686</td>
        </tr>
    </table>
    <br>
    <br>
    <br>
    <table id="invoice-details" class="invoice-details">
        <caption>
            Invoice details
        </caption>
        <tr>
            <td>Invoice number:</td>
            <td id="invoice-number"></td>
            <td>Tax Date:</td>
            <td id="tax-date">${reservation_timestamp}</td>
        </tr>
        <tr>
            <td>Order number:</td>
            <td id="order-number">${order}</td>
            <td>Payment:</td>
            <td id="payment">£${grossTotal}</td>
        </tr>

    </table>


    <br>
    <br>
    <br>

    <table id="item-list" class="item-list">
        <thead>
        <tr>
            <th>Name</th>
            <th>Unit Price</th>
            <th>Quantity</th>
            <th>Net</th>
            <th>${taxType}%</th>
            <th>${taxType}</th>
            <th>Gross</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${items}" varStatus="row">
            <c:set var="item" value="${entry.key}"/>
            <c:set var="net" value="${item.price * entry.value}"></c:set>
            <c:set var="tax" value="${tax_rate * net /100}"></c:set>
            <tr>
                <td style="width: 20%"><c:out value="${item.name}"/></td>
                <td style="width: 10%"><c:out value="£${item.price}"/></td>
                <td style="width: 10%"><c:out value="${entry.value}"/></td>
                <td style="width: 10%">£<c:out value="${net}"/></td>
                <td style="width: 10%"><c:out value=""/>${tax_rate}%</td>
                <td style="width: 10%">£<c:out value=""/>${tax}</td>
                <td style="width: 10%">£<c:out value=""/>${net + tax}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <br>
    <table id="tax">
        <tr>
            <td>Net total:</td>
            <td id="net-total">£${subTotal}</td>
        </tr>
        <tr>
            <td>VAT total:</td>
            <td id="total-vat">£${totalVat}</td>
        </tr>
        <tr>
            <td>Duty Tax:</td>
            <td id="total-duty">£${totalDuty}</td>
        </tr>

        <tr style="border-bottom: 1pt solid black;border-top:1pt solid black">
            <td>Gross total:</td>
            <td id="gross-total">£${grossTotal}</td>
        </tr>
    </table>
    <button id="close-invoice" onclick="window.close();">
        Close
    </button>
</section>


</body>
</html>