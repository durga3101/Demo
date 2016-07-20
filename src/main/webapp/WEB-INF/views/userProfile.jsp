<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-action">Your details</div>
        <div id="user-details" >
            ${userDetail.account_name} - ${userDetail.email_address} - ${userDetail.country}<br />
        </div>
        <div id = "shipping_address">
            ${address.street_1}<br>
            ${address.street_2}<br>
            ${address.city}, ${address.state}, ${address.postcode}<br>

        </div>

		<div class="page-action">Your Orders</div>
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
            <c:forEach var="item" items="${items}" varStatus="row">
                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td>£<c:out value="${item.price}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.type}"/></td>
                </tr>
             </c:forEach>
            </tbody>
        </table>

<%@ include file="footer.jsp" %>