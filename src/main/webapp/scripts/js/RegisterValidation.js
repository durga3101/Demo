var fields = ['email', 'name', 'password', 'phoneNumber'];

function inputFieldSelector(field) {
    return '#fld_' + field;
}

function errorSelector(field) {
    return '#' + field + '_field';
}

function validateRegistrationForm() {
    var validate = true;
    fields.forEach(function(field) {
        validate &= isValidate(inputFieldSelector(field));
    });
    return validate;
}

function showErrorMessage() {
    fields.forEach(function(field) {
        isValidate(inputFieldSelector(field))? hideErrorMessage(errorSelector(field)): displayErrorMessage(errorSelector(field));
    });
}

function isValidate(selector) {
    if (selector === 'email') {
        return $(selector).val().indexOf("@") >= 0;
    }
    else return isEmpty(selector);
}

function isEmpty(id) {
    return $(id).val().trim() != "";
}

function displayErrorMessage(id) {
    $(id + " .text-error").css("display", "inline-block");
}
function hideErrorMessage(id) {
    $(id + " .text-error").css("display", "none")
}

