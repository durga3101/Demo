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

        var fieldIsPresent = function (fieldName) {
            return !!args[fieldName];
        };

        return mandatoryFieldNames.reduce(function (lastElementsWerePresent, currentFieldName) {
            return lastElementsWerePresent && fieldIsPresent(currentFieldName);
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

