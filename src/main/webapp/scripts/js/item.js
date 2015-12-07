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
            validate = validate && isPriceValid(field);
        } else {
            validate = validate && validator.isFieldEmpty(field);
        }
    });

    return validate;
}

function showItemErrorMessage() {
    fields.forEach(function(field) {
        if (field === "#price")
            showPriceErrorMessage();
        else
            validator.isFieldEmpty(field)? validator.hideErrorMessage(errorSelector(field)): validator.displayErrorMessage(errorSelector(field));
    });
}

function showPriceErrorMessage() {
    if(!validator.isFieldEmpty("#price")) {
        validator.displayErrorMessage("#not_empty");
    } else {
        validator.hideErrorMessage("#not_empty");
        isPriceValid("#price") ? validator.hideErrorMessage("#not_validate_number") : validator.displayErrorMessage("#not_validate_number");
    }
}

function isPriceValid(field) {
    return validator.isFieldEmpty(field) && $(field).val() <= 100000;
}

function errorSelector(field) {
    return field + '_field' + " .text-error";
}

