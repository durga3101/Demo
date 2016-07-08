var fields = ['email', 'name', 'password', 'confirmPassword', 'phoneNumber', 'country'];
function inputFieldSelector(field) {
    return '#fld_' + field;
}

function errorSelector(field) {
    return '#' + field + '_field' + " .text-error";
}

function validateRegistrationForm() {
    var validate = true;
    fields.forEach(function (field) {
        validate = validate && isValid(inputFieldSelector(field));
    });
    return validate;
}

function checkPasswords() {
    if ($("#fld_password").val() === $("#fld_confirmPassword").val()) {
        return true;
    }
    $(errorSelector("confirmPassword")).html("Passwords do not match!");
    return false;
}
function showRegisterErrorMessage() {
    fields.forEach(function (field) {
        isValid(inputFieldSelector(field)) ? validator.hideErrorMessage(errorSelector(field)) : validator.displayErrorMessage(errorSelector(field));
    });
}
function isValid(selector) {
    if(validator.isFieldEmpty($(selector).val())){
        return false; 
    }
    if (selector === "#fld_email") {
        return $(selector).val().indexOf("@") >= 0;
    }
    if (selector === "#fld_country") {
        return $(selector).val() != "";
    }
    if (selector === "#fld_phoneNumber") {
        var numberFormat = /^\d+\-?\d+$/;
        var phoneNumber = $(selector).val();
        return numberFormat.test(phoneNumber);
    }

    if (selector === "#fld_password") {
        if(!validator.validatePassword($(selector).val())){
            $(errorSelector("password")).html("Must enter a valid Password!");
            return false;
        }
        return true;

    }
    if (selector === "#fld_confirmPassword") {
        return checkPasswords();
    }
    return true;
}


