var fields = ['email', 'name', 'password', 'phoneNumber'];

function inputFieldSelector(field) {
    return '#fld_' + field;
}

function errorSelector(field) {
    return '#' + field + '_field' + " .text-error";
}

function validateRegistrationForm() {
    var validate = true;
    fields.forEach(function(field) {
        validate = validate && isValid(inputFieldSelector(field));
    });
    return validate;
}

function showRegisterErrorMessage() {
    fields.forEach(function(field) {
        isValid(inputFieldSelector(field))? validator.hideErrorMessage(errorSelector(field)): validator.displayErrorMessage(errorSelector(field));
    });
}

function isValid(selector) {
    if (selector === "#fld_email") {
        return $(selector).val().indexOf("@") >= 0;
    }
    else return validator.isFieldEmpty(selector);
}

