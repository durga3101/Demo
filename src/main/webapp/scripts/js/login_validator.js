function isLoginValid(email) {
    return $(email).val().indexOf("@") >= 0;
}

function showLoginMessage() {
    
    var submit = document.getElementById("login-submit");
    function init() {
        submit.onclick = function (e) {
            if (isLoginValid("#login_email")){
                validator.hideErrorMessage("#login-error-message")
            } else {

                validator.displayErrorMessage("#login-error-message")
                e.preventDefault();
            } 
            
        }
    }
    
    init();

}