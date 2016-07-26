<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-action">Your details</div>
        <div id="user-details" >

            <p><b>${userDetail.account_name}</b></p>
            <p><i>Email:</i> ${userDetail.email_address}</p>
            <p><i>Country:</i> ${userDetail.country}</p>
            <p><i>Phone number:</i> ${userDetail.phone_number}</p>
            <b>Shipping address:</b>
        </div>
        <div id = "shipping_address">
            <c:choose>
                <c:when test = "${addressAvailable}">
                    <p>${address.street_1}</p>
                    <p>${address.street_2}</p>
                    <p>${address.city}, ${address.state}, ${address.postcode}</p>
                </c:when>
                <c:otherwise>
                    Sorry, no saved address.
                </c:otherwise>
            </c:choose>
        </div>

		<div class="page-action">Your Orders</div>

        <c:choose>
            <c:when test="${empty items}">
                No orders yet.
            </c:when>
        <c:otherwise>
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
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

<%@ include file="footer.jsp" %>