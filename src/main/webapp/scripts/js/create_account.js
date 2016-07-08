var fields = ['email', 'name', 'password', 'phoneNumber', 'country'];

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

function showRegisterErrorMessage() {
    fields.forEach(function (field) {
        isValid(inputFieldSelector(field)) ? validator.hideErrorMessage(errorSelector(field)) : validator.displayErrorMessage(errorSelector(field));
    });
}

function isValid(selector) {
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
    return validator.isFieldEmpty(selector);
}

