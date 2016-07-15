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
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${item.name}</td>
        <td>${item.price}</td>
        <td>${item.description}</td>
        <td>${item.type}</td>
    </tr>
    </tbody>
</table>

<div id="modal" style="display: none">
    <button id="modal-close">Close</button>
</div>

<%@ include file="footer.jsp" %>