<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="pageTitle" scope="request" value="Login"/>
<%@ include file="header.jsp" %>

    <c:choose>
        <c:when test="${not empty error}">
            <div id="loginError" class="page-action error">
                Your login attempt was not successful, try again.<br /> Caused :
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:when>
        <c:otherwise>
            <div id="TrailBlazers - Login" class="page-action">
                Login with your email and password
            </div>
        </c:otherwise>
    </c:choose>

<div class="row">
    <div id="login-div" class="col-4 centered">

	<form name='f' action="<c:url value='j_spring_security_check' />" method="post">
        <div id="login_email_field">
            <label><b>Email</b></label>
            <div class="controls">
                <input type='text' name='j_username' id="login_email" placeholder="somebody@something.com"></td>
                <span class="text-error" id="login-error-message">Must enter a valid email!</span>
            </div>
        </div>

        <div>
            <label><b>Password</b></label>
            <div class="controls">
                <input type="password" name="j_password" placeholder="Password">
            </div>
        </div>

        <div>
            <div class="controls">
                <button type="submit" id = "login-submit" name="submit">Sign in</button>
            </div>
        </div>

	</form>

    </div><!-- end col -->
</div><!-- end row -->

<%@ include file="footer.jsp" %>
<script type="text/javascript" src="<c:url value='/scripts/js/login_validator.js' />"></script>
<script type="text/javascript" src="<c:url value='/scripts/js/field_validator.js' />"></script>
<script type="text/javascript">
    $(function () {
        new showLoginMessage();
    })
</script>

