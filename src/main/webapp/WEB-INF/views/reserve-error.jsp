<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Request Error"/>
<%@ include file="header.jsp" %>

<link rel="stylesheet" href="../../scripts/css/reserve-error.css" type="text/css"/>


<div class="page-action">Oh no! There was an error processing your request. Please try again.</div>

<button onclick="location.href = '/payment';">Back to checkout</button>

<%@ include file="footer.jsp" %>