<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Reserve Item"/>
<%@ include file="header.jsp" %>

<script type="text/javascript">
    $(function () {
        new SurveyPopUp().showSurvey();
    })
</script>

<div class="page-action">Thank you. Your order will be delivered in ten days!</div>

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
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.price * entry.value}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.type}"/></td>
            <td><c:out value="${entry.value}"/></td>
            <td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div id="modal" style="display: none">
    <%@ include file="survey/form.jsp" %>
    <%@ include file="survey/confirmation.jsp" %>

</div>
<%@ include file="footer.jsp" %>