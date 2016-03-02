
var fields = ['#name', '#price', '#type', '#description', '#quantity'];

function validateForm() {
    var itemView = new ItemView();
    var itemModel = itemView.getModel();

    return itemModel.validate();
}

function showItemErrorMessage() {
    fields.forEach(function (field) {
        if (field === "#price")
            showPriceErrorMessage();
        else
            validator.isFieldEmpty(field) ? validator.hideErrorMessage(errorSelector(field)) : validator.displayErrorMessage(errorSelector(field));
    });
}

function showPriceErrorMessage() {
    if (!validator.isFieldEmpty("#price")) {
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

