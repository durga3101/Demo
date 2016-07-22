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
    <h1>Invoice</h1>
    <table id="customer-details">
        <div>To:</div>
        <tr>
            <td>Client </td>
            <td>Freewheelers Ltd.</td>
        </tr>
        <tr>
            <td>Company</td>
            <td></td>
        </tr>
        <tr>
            <td>Address line 1</td>
            <td id = "customer-address1">${userDetails.street_1}</td>
        </tr>
        <tr>
            <td>Address line 2 </td>
            <td id = "customer-address2">${userDetails.street_2}</td>
        </tr>
        <tr>
            <td>City</td>
            <td id="customer-city">${userDetails.city}</td>
        </tr>
        <tr>
            <td>Post Code</td>
            <td id="customer-postalCode">${userDetails.postcode}</td>
        </tr>
        <tr>
            <td>Country</td>
            <td id="customer-someCountry">${country}</td>
        </tr>

    </table>
    <table id="item-list" class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Unit Price</th>
            <th>Quantity</th>
            <th>Net</th>
            <th>VAT%</th>
            <th>VAT</th>
            <th>Gross</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${items}" varStatus="row">
            <c:set var="item" value="${entry.key}"/>
            <c:set var="net" value="${item.price * entry.value}"></c:set>
            <c:set var="vat" value="${vat_rate * net /100}"></c:set>
            <tr>
                <td style="width: 20%"><c:out value="${item.name}"/></td>
                <td style="width: 10%"><c:out value="${item.price}"/></td>
                <td style="width: 10%"><c:out value="${entry.value}"/></td>
                <td style="width: 10%">£<c:out value="${net}"/></td>
                <td style="width: 10%"><c:out value=""/>${vat_rate}%</td>
                <td style="width: 10%">£<c:out value=""/>${vat}</td>
                <td style="width: 10%">£<c:out value=""/>${net + vat}</td>
            </tr>
        </c:forEach>
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
            <tr>
                <td>Gross total:</td>
                <td id="gross-total">£${grossTotal}</td>
            </tr>

        </table>

        </tbody>
    </table>
</section>

<button id="close-invoice" onclick="window.close();">
    Close
</button>
</body>
</html>