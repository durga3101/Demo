function ItemValidation(errorDisplayStrategy) {
    this.errorDisplayStrategy = errorDisplayStrategy;
}

ItemValidation.prototype.validate = function (itemForm) {
    var NUMERICAL_PATTERN = /^[0-9.]+$/;
    var isValid = true;

    if (!itemForm.price.match(NUMERICAL_PATTERN)) {
        this.errorDisplayStrategy("Price should be a float only");
        isValid = false;
    }

    return isValid;
};

function validatePriceInputType() {
    var errorDisplayStrategy = function (error) {
        alert(error);
    };
    var itemValidation = new ItemValidation(errorDisplayStrategy);

    return itemValidation.validate(serializeObject(document.forms["addItem"]));
}

var fields = ['#name', '#price', '#type', '#description', '#quantity'];

function validateForm() {
    validatePriceInputType();

    var validate = true;
    fields.forEach(function(field) {
        validate = validate && isValidate(field);
    });

    return validate;
}

function showErrorMessage() {
    fields.forEach(function(field) {
        if (field === "#price")
            showPriceErrorMessage();
        else
            isValidate(field)? hideErrorMessage(errorSelector(field)): displayErrorMessage(errorSelector(field));
    });
}

function showPriceErrorMessage() {
    isEmpty("#price") ? hideErrorMessage("#not_empty") : displayErrorMessage("#not_empty");

    isPriceNumberValidate("#price") ? hideErrorMessage("#not_validate_number") : displayErrorMessage("#not_validate_number");
}

function isPriceNumberValidate(selector) {
    return $(selector).val() < 10000;
}
function isValidate(selector) {
    if (selector === '#price') {
        return isPriceNumberValidate(selector) && isEmpty(selector);
    }
    return isEmpty(selector);
}

function errorSelector(field) {
    return field + '_field' + " .text-error";
}

function isEmpty(id) {
    return $(id).val().trim() != "";
}

function displayErrorMessage(selector) {
    $(selector).css("display", "inline-block");
}
function hideErrorMessage(selector) {
    $(selector).css("display", "none")
}

