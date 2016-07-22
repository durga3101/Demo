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
        <table id = "item-list" class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Net</th>
                <th>Vat%</th>
                <th>Vat</th>
                <th>Gross</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="entry" items="${items}" varStatus="row">
                <c:set var="item" value="${entry.key}" />
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
            </tbody>
        </table>
    </section>

    <button id="close-invoice" onclick="window.close();">
        Close
    </button>
</body>
</html>