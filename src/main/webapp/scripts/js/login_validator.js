function isLoginValid(email) {
    return $(email).val().indexOf("@") >= 0;
}

function showLoginMessage() {
    
    var submit = document.getElementById("login-submit");
    var loginErrorMessage = document.getElementById('login-error-message');
    function init() {
        submit.onclick = function (e) {
            if (!isLoginValid("#login_email")){
                console.log("display message")
                loginErrorMessage.style.display = 'inline-block';
                e.preventDefault();
            } else {
                console.log("hide message")
                loginErrorMessage.style.display = 'none';
            } 
            
        }
    }
    
    init();

}