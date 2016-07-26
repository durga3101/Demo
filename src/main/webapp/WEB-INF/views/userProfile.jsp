<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page-action">Your details</div>
        <div id="user-details" >
            <table class="userProfile_Details">
                <thread><b>${userDetail.account_name}</b></thread>
                <tr>
                    <td><i>Email</i></td>
                    <td>${userDetail.email_address}</td>
                </tr>
                <tr>
                    <td><i>Country</i></td>
                    <td>${userDetail.country}</td>
                </tr>
                <tr>
                    <td><i>Phone number</i></td>
                    <td>${userDetail.phone_number}</td>
                </tr>
            </table>

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