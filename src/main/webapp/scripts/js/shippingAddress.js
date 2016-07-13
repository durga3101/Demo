var fields = ['street1', 'city', 'state', 'postcode'];
function inputFieldSelector(field) {
    return '#fld_' + field;
}

function errorSelector(field) {
    return '#' + field + '_field' + " .text-error";
}

function validateShippingAddressForm() {
    console.log("validate address")
    var validate = true;
    fields.forEach(function (field) {
        validate = validate && isNotNull(inputFieldSelector(field));
    });
    return validate;
}
function showShippingAddressErrorMessage() {
    fields.forEach(function (field) {
        isNotNull(inputFieldSelector(field)) ? validator.hideErrorMessage(errorSelector(field)) : validator.displayErrorMessage(errorSelector(field));
    });
}
function isNotNull(selector) {
    if($(selector).val()===""){
        return false;
    }
    return true;
}