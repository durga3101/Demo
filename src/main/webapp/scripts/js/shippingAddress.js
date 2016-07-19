var fields = ['street1', 'street2', 'city', 'state', 'postcode'];
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
    if ($(selector).val() === "" && (selector) !== "#fld_street2") {
        return false;
    }
    if ((selector) === "#fld_street1") {
        if ($(selector).val().length > 255) {
            $(errorSelector("street1")).html("Must enter valid street details");
            return false;
        }
    }
    if ((selector) === "#fld_street2") {
        if ($(selector).val().length > 255) {
            $(errorSelector("street2")).html("Must enter valid street details");
            return false;
        }
    }
    if ((selector) === "#fld_city") {
        if ($(selector).val().length > 100) {
            $(errorSelector("city")).html("Must enter valid city");
            return false;
        }
    }
    if ((selector) === "#fld_state") {
        if ($(selector).val().length > 100) {
            $(errorSelector("state")).html("Must enter valid state/province");
            return false;
        }
    }
    if ((selector) === "#fld_postcode") {
        var format = /^[a-zA-Z0-9]*\-?\ ?[a-zA-Z0-9]*$/;
        var postalcode = $(selector).val();
        if (!(format.test(postalcode))) {
            return false;
        }
        if ($(selector).val().length < 4) {
            $(errorSelector("postcode")).html("Must enter valid postal code");
            return false
        }
        if ($(selector).val().length > 20) {
            $(errorSelector("postcode")).html("Must enter valid postal code");
            return false;
        }
    }
    return true;
}