<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Create Account"/>

<%@ include file="../header.jsp" %>

<div class="page-action">
    Create a new account
</div>

<c:if test="${not empty validationMessage.errors}">
    <div id="resultsMessage" class="page-action error">
        There were errors. Please check your input.
    </div>
</c:if>

<form id="register_form" action="/account/create" method="post" onsubmit="return validateRegistrationForm();">
    <div id="email_field">
        <label for="fld_email">Email</label>
        <div class="controls">
            <input type="text" id="fld_email" placeholder="somebody@something.com" name="email">
            <span class="text-error">Must enter a valid email!</span>
        </div>
    </div>

    <div id="password_field">
        <label for="fld_password">Password</label>
        <div class="controls">
            <input type="password" id="fld_password" placeholder="secret password" name="password">
            <span class="text-error">Must enter a password!</span>
        </div>
    </div>

    <div id="name_field">
        <label for="fld_name">Name</label>
        <div class="controls">
            <input type="text" id="fld_name" placeholder="Your Name" name="name">
            <span class="text-error">Must enter a name!</span>
        </div>
    </div>

    <div id="phoneNumber_field">
        <label for="fld_phoneNumber">Phone Number</label>
        <div class="controls">
            <input type="text" id="fld_phoneNumber" placeholder="555-123456" name="phoneNumber">
            <span class="text-error">Must enter a phone number!</span>
        </div>
    </div>

    <div id="country_field">
        <label for="fld_country">Country</label>
        <div class="controls">
            <select id="fld_country" name="country">
                <option value="" >Choose A Country</option>
                <option value="Italy">Italy</option>
                <option value="UK">UK</option>
                <option value="USA">USA</option>
                <option value="France">France</option>
                <option value="Germany">Germany</option>
                <option value="Canada">Canada</option>
            </select>
            Note: If your country is not listed then we don't ship there. Please check back later.
            <span class="text-error">Must select a country!</span>
        </div>
    </div>

    <div>
        <div class="controls">
            <button type="submit" id="createAccount" value="Submit" onclick="showRegisterErrorMessage()">Create
                Account
            </button>
        </div>
    </div>

</form>

<%@ include file="../footer.jsp" %>
<script type="text/javascript" src="<c:url value='/scripts/js/create_account.js' />"></script>
