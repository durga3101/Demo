<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Order Summary"/>
<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-action">Thank you. Your order will be delivered in ten days!</div>

<a id="view-invoice" href="/invoice" style="color:#144d8a ;margin-left:45%; margin-bottom: 5%;" target="_blank">
    View invoice
</a>

<table class="table">
    <thead>

    <tr>

        <td>Order ID</td>
        <td>Image</td>
        <td>Name</td>
        <td>Price</td>
        <td>Description</td>
        <td>Type</td>
        <td>Quantity</td>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="entry" items="${items}" varStatus="row">
        <c:set var="item" value="${entry.key}"/>

        <tr>
            <td style="width: 10%" id="order_id"><c:out value="${order}"/></td>
            <td><img height="80px" width="100px" src="<c:out value="${item.imageURL}"/>"/></td>
            <td style="width: 10%"><c:out value="${item.name}"/></td>
            <td style="width: 10%">£<c:out value="${item.price * entry.value}"/></td>
            <td style="width: 30%"><c:out value="${item.description}"/></td>
            <td style="width: 15%"><c:out value="${item.type}"/></td>
            <td style="width: 5%"><c:out value="${entry.value}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

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
