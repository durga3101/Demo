var fields = ['email', 'name', 'password', 'phoneNumber', 'country'];
var specialCharacters = ['!','"','#','$','%','&','(',')','*','+','-','.','/',':',';','<','=','>','?','@','[',']','^','_','`','{','|','}','~'];
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

function validatePassword(val) {

    if(!(/[0-9]/.test(val))){
        return false;
    }
    if(!(/[a-z]/.test(val))){
        return false;
    }
    if(!(/[A-Z]/.test(val))){
        return false;
    }
    var index;
    for(index = 0; index < specialCharacters.length; index++){
        if(val.indexOf(specialCharacters[index]) >= 0){
            break;
        }
    }
    if(index == specialCharacters.length){
        return false;
    }

    var length = val.length;
    return length >= 8 && length <=20;
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
    if(selector === "#fld_password"){
        return validatePassword($(selector).val());

    }
    return validator.isFieldEmpty(selector);
}


