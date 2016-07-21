<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Order Summary"/>
<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-action">Thank you. Your order will be delivered in ten days!</div>

<a id="view-invoice" href="/invoice" target="_blank">
    <button>View invoice</button>
</a>

<table class="table">
    <thead>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Type</th>
            <th>Quantity</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="entry" items="${items}" varStatus="row">
            <c:set var="item" value="${entry.key}" />

            <tr>
                <td style="width: 30%"><c:out value="${item.name}"/></td>
                <td style="width: 10%">£<c:out value="${item.price * entry.value}"/></td>
                <td style="width: 40%"><c:out value="${item.description}"/></td>
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
