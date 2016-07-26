<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Order Summary"/>
<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-action">Thank you. Your order will be delivered in ten days!</div>

<table class="table">
    <thead>

    <tr id="summary-table-head" class="bold">
        <td class="padded-col">Order ID</td>
        <td class="padded-col">Image</td>
        <td class="padded-col">Name</td>
        <td class="padded-col">Price</td>
        <td class="padded-col">Description</td>
        <td class="padded-col">Type</td>
        <td class="padded-col">Qty</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="entry" items="${items}" varStatus="row">
        <c:set var="item" value="${entry.key}"/>

        <tr id="summary-table-body">
            <td class="padded-col" style="width: 10%" id="order_id"><c:out value="${order}"/></td>
            <td class="padded-col" style="width: 10%"><img width="100%" height="same-as-width"src="<c:out value="${item.imageURL}"/>"/></td>
            <td class="padded-col" style="width: 22%"><c:out value="${item.name}"/></td>
            <td class="padded-col" style="width: 10%">£<c:out value="${item.price * entry.value}"/></td>
            <td class="padded-col" style="width: 35%"><c:out value="${item.description}"/></td>
            <td class="padded-col" style="width: 10%"><c:out value="${item.type}"/></td>
            <td class="padded-col" style="width: 3%"><c:out value="${entry.value}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<hr />

<div id="invoice-container" class="right large bold">

    Thank you for choosing Freewheelers.

    <a id="view-invoice" href="/invoice" target="_blank">
        Click to view invoice
    </a>
</div>

<div id="modal" style="display: none;">
    <%@ include file="survey/form.jsp" %>
    <%@ include file="survey/confirmation.jsp" %>
</div>

<%@ include file="footer.jsp" %>

<script type="text/javascript" src="<c:url value='/scripts/js/modal.js' />"></script>
<script type="text/javascript">
    $(function () {
        new SurveyPopUp().showSurvey();
    })
</script>
