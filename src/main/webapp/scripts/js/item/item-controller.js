function ItemController () {
    this.validateForm = function () {
        var itemView = new ItemView();
        var itemModel = itemView.getModel();

        return itemModel.validate();
    }
}

var itemController = new ItemController();

var fields = ['#name', '#price', '#type', '#description', '#quantity'];

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
        validator.displayErrorMessage("#price_empty");
    } else {
        validator.hideErrorMessage("#price_empty");
        isPriceValid("#price") ? validator.hideErrorMessage("#price_out_of_bounds") : validator.displayErrorMessage("#price_out_of_bounds");
    }
}

function isPriceValid(field) {
    return validator.isFieldEmpty(field) && $(field).val() <= 100000;
}

function errorSelector(field) {
    return field + '_field' + " .text-error";
}

