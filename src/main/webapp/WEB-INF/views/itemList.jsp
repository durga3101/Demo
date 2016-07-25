<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Manage Items"/>
<%@ include file="header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

		<script type="text/javascript" src="<c:url value='/scripts/js/extensions.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/js/UpdateItemsCheckbox.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/js/RowSelector.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/js/item/item-model.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/js/item/item-view.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/js/item/item-update.js' />"></script>

        <script type="text/javascript">
            $(function () {
                new ItemView($('#add_item'));
            });
        </script>

		<h1>Manage Items</h1>

		<form:form name="addItem" id="add_item" action="" method="post" modelAttribute="item">

            <div class="page-action">New Item</div>

            <c:if test="${not empty errors}">
                <div id="resultsMessage" class="page-action error">
                    There were errors. Please check your input.
                </div>
            </c:if>

            <div>
                <div id="name_field">
                    <form:label for="name" path="name">Name</form:label>
                    <div class="controls">
                        <form:input path="name"/>
                        <span class="text-error" id="name_empty">Please enter Item Name</span>
                    </div>
                </div>

                <div id="price_field">
                    <form:label for="price" path="price">Price (£)</form:label>
                    <div class="controls">
                        <form:input path="price"/>

                        <span class="text-error" id="price_empty">Please enter Item Price</span>
                        <span class="text-error" id="price_out_of_bounds">Must be less than or equal to 99999</span>
                    </div>
                </div>

                <div id="type_field">
                    <form:label for="type" path="type">Type</form:label>
                    <div class="controls">
                        <form:select path="type">
                            <form:option value="" label="Select"/>
                            <form:options items="${itemTypes}"/>
                        </form:select>
                        <span class="text-error" id="type_empty">Please enter Item Type</span>
                    </div>
                </div>

                <div id="description_field">
                    <form:label for="desription" path="description">Description</form:label>
                    <div class="controls">
                        <form:textarea path="description"/>
                        <span class="text-error"  id="description_empty">Please enter Item Description</span>
                    </div>
                </div>

                <div id="quantity_field">
                    <form:label for="quantity" path="quantity">Quantity</form:label>
                    <div class="controls">
                        <form:input path="quantity"/>
                        <span class="text-error" id="quantity_empty">Please enter Item Quantity</span>
                    </div>
                </div>

                <div id="imgURL_field">
                    <form:label for="imageURL" path="imageURL">Image URL</form:label>
                    <div class="controls">
                        <form:input path="imageURL"/>
                    </div>
                </div>

                <div>
                    <div class="controls">
                        <button type="submit" value="Create new item" id="createItem">Create Item</button>
                    </div>
                </div>

                </div>
		</form:form>

<script type="text/javascript">
    $(function () {
        new ItemViewUpdate($('#update_item'));
    });
</script>

		<form:form action="/item" method="post" modelAttribute="itemGrid" id="update_item">

            <div class="page-action">Update Items</div>

            <div>
			    <table class="table">
				<thead><tr><th><input type="checkbox" class="toggleAll" /></th>
				<th>Name</th>
				<th>Price (£)</th>
				<th>Description</th>
				<th>ItemType</th>
				<th>Quantity</th>
                <th>Image URL</th>
				</tr></thead>
				<tbody >
					<c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row" >
						<tr>
							<td>
								<form:input type="hidden" disabled="true" path="itemMap[${itemEntry.key}].itemId" />
								<input type="checkbox" class="rowSelector" />
							</td>
							<td>
								<form:errors path="itemMap[${itemEntry.key}].name" class="text-error" />
								<form:input disabled="true" path="itemMap[${itemEntry.key}].name" id="name"/>
							</td>
							<td>
								<form:errors path="itemMap[${itemEntry.key}].price" class="text-error" />
								<form:input disabled="true" path="itemMap[${itemEntry.key}].price" class="price" id="price${itemEntry.key}"/>
                                <span class="text-error" id="empty_price${itemEntry.key}" >Please enter valid Item Price</span>

                            </td>
							<td>
								<form:errors path="itemMap[${itemEntry.key}].description" class="text-error" />
								<form:input path="itemMap[${itemEntry.key}].description" disabled="true" id="description"/>
							</td>
							<td>
                            	<form:errors path="itemMap[${itemEntry.key}].type" class="text-error" />
       							<form:input path="itemMap[${itemEntry.key}].type" disabled="true" id="type"/>
     					    </td>
     					    <td>
                                <form:errors path="itemMap[${itemEntry.key}].quantity" class="text-error" />
                                <form:input path="itemMap[${itemEntry.key}].quantity" disabled="true" class="quantity" id="quantity${itemEntry.key}"/>
                                <span class="text-error" id="empty_quantity${itemEntry.key}" >Please enter valid Item Quantity</span>
                            </td>
                            <td>
                                <form:input path="itemMap[${itemEntry.key}].imageURL" id="image_url${itemEntry.value.name}" disabled="true"/>
                            </td>
						</tr>
					</c:forEach>
				</tbody>
			    </table>

			<p>
                <button type="submit" value="Update all enabled items" name="update">Update all enabled items</button>
                <button type="submit" value="Delete all enabled items" name="delete">Delete all enabled items</button>
			</p>

            </div>
		</form:form>
<%@ include file="footer.jsp" %>
