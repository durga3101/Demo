<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<%@ include file="header.jsp" %>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
        <th>Type</th>
        <th>Quantity</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}" varStatus="row">
        <tr>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><c:out value="${item.description}"/></td>
            <td><c:out value="${item.type}"/></td>
            <td><c:out value="${item.quantity}"/></td>
            <td>
                <form:form action="reserve" method="post" modelAttribute="item">
                    <form:hidden path="itemId" value="${item.itemId}"/>
                    <button class="reserve-button" type="submit" name="reserve" id="reserve" value="Reserve Item">
                        Reserve Item
                    </button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="footer.jsp" %>