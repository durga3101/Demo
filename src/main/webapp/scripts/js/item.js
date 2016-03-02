function ItemModel(parameters) {
    var args = parameters || {};
    var mandatoryFieldNames = ["price", "name", "type", "description", "quantity"];

    var priceShouldBeANumber = function () {
        var FLOAT_PATTERN = /^[0-9.]+$/;
        return args.price.match(FLOAT_PATTERN);
    };

    var priceShouldBeLessThanThreshold = function () {
        return parseInt(args.price) <= 100000;
    };

    var mandatoryFieldsShouldBePresent = function () {
        return mandatoryFieldNames.reduce(function (previous, current) {
            return previous && args[current];
        }, true);
    };

    var quantityShouldBeInteger = function () {
        var INTEGER_PATTERN = /^[0-9]+$/;

        return args.quantity.match(INTEGER_PATTERN);
    };

    this.validate = function () {
        return mandatoryFieldsShouldBePresent()
            && priceShouldBeANumber()
            && priceShouldBeLessThanThreshold()
            && quantityShouldBeInteger();
    };
}

var fields = ['#name', '#price', '#type', '#description', '#quantity'];

function ItemView(){
    var itemModelParameters = {
        price : $("#price").val(),
        name : $("#name").val(),
        type : $("#type").val(),
        description : $("#description").val(),
        quantity : $("#quantity").val()
    };

    this.getModel = function(){
        return new ItemModel(itemModelParameters);
    };
}

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

