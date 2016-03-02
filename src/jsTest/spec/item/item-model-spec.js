describe("ItemModel", function () {
    var validItemModelParameters;
    var extendWith = $.extend;

    beforeEach(function () {
        validItemModelParameters = {
            price: "1",
            name: "name",
            type: "type",
            description: "description",
            quantity: "1"
        };
    });


    it("validates when price is a number and name, type, description and quantity are present", function () {
        var itemModel = new ItemModel(validItemModelParameters);
        expect(itemModel.validate()).toBeTruthy();
    });

    it("fails to validate when any of the fields are missing", function () {
        var itemArgsWithMissingNameAndType = extendWith(validItemModelParameters, {
            name: "",
            type: ""
        });

        var itemModelWithoutNameAndType = new ItemModel(itemArgsWithMissingNameAndType);
        expect(itemModelWithoutNameAndType.validate()).toBeFalsy();
    });

    it("fails to validate when price is not a number", function () {

        var itemArgsWithNonNumberPrice = extendWith(validItemModelParameters, {
            price : "aaa"
        });
        var itemModel = new ItemModel(itemArgsWithNonNumberPrice);

        expect(itemModel.validate()).toBeFalsy();
    });

    it("fails to validate when price is empty", function() {
        var itemArgsWithEmptyPrice = extendWith(validItemModelParameters, {
            price : ""
        });

        var itemModel = new ItemModel(itemArgsWithEmptyPrice);
        expect(itemModel.validate()).toBeFalsy();
    });

    it("fails to validate when price is greater than threshold", function() {
        var itemArgsWithPriceGreaterThanThreshold = extendWith(validItemModelParameters, {
            price : "100001"
        });

        var itemModel = new ItemModel(itemArgsWithPriceGreaterThanThreshold);
        expect(itemModel.validate()).toBeFalsy();

    });

    it("fails to validade when quantity is not a integer", function () {
        var itemArgsWithInvalidQuantity = extendWith(validItemModelParameters, {
            quantity: "blah"
        });

        var itemModel = new ItemModel(itemArgsWithInvalidQuantity);
        expect(itemModel.validate()).toBeFalsy();
    });
});

