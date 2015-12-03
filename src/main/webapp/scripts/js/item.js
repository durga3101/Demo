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
        if(field === "#price") {
            validate = validate && isValidPrice(getValue(field));
        } else {
            validate = validate && isValueEmpty(getValue(field));
        }
    });

    return validate;
}

function showItemErrorMessage() {
    fields.forEach(function(field) {
        if (field === "#price")
            showPriceErrorMessage();
        else
            isValueEmpty(getValue(field))? hideErrorMessage(errorSelector(field)): displayErrorMessage(errorSelector(field));
    });
}

function showPriceErrorMessage() {
    isValueEmpty(getValue("#price")) ? hideErrorMessage("#not_empty") : displayErrorMessage("#not_empty");

    isPriceNumberValidate(getValue("#price")) ? hideErrorMessage("#not_validate_number") : displayErrorMessage("#not_validate_number");
}


function getValue(id) {
    return $(id).val();
}

function isPriceNumberValidate(value) {
    return value < 10000;
}
function isValidPrice(value) {
    return isPriceNumberValidate(value) && isValueEmpty(value);
}

function errorSelector(field) {
    return field + '_field' + " .text-error";
}

function isValueEmpty(value) {
    return value.trim() != "";
}

function displayErrorMessage(selector) {
    $(selector).css("display", "inline-block");
}
function hideErrorMessage(selector) {
    $(selector).css("display", "none")
}

