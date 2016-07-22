function isLoginValid(email) {
    return $(email).val().indexOf("@") >= 0;
}

function showLoginMessage() {
    
    var submit = document.getElementById("login-submit");
    var loginErrorMessage = document.getElementById('login-error-message');
    function init() {
        submit.onclick = function (e) {
            if (!isLoginValid("#login_email")){
                loginErrorMessage.style.display = 'inline-block';
                e.preventDefault();
            } else {
                loginErrorMessage.style.display = 'none';
            } 
            
        }
    }
    
    init();

}