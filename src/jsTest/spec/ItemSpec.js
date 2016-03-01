describe("item", function () {
    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input id="price" type="text">          \
                                ');
    }

    describe("ItemModel", function () {

        var correctItemArgs;

        var extendWith = $.extend;

        beforeEach(function () {
            correctItemArgs = {
                price: "1",
                name: "name",
                type: "type",
                description: "description",
                quantity: "quantity"
            };
        });


        it("validates when price is a number and name, type, description and quantity are present", function () {
            var itemModel = new ItemModel(correctItemArgs);
            expect(itemModel.validate()).toBeTruthy();
        });

        it("fails to validate when any of the fields are missing", function () {
            var itemArgsWithMissingNameAndType = extendWith({}, correctItemArgs, {
                name: "",
                type: ""
            });
            var itemModelWithoutNameAndType = new ItemModel(itemArgsWithMissingNameAndType);
            expect(itemModelWithoutNameAndType.validate()).toBeFalsy();
        });

        it("fails to validate when price is not a number", function () {

            var itemArgsWithNonNumberPrice = extendWith({}, correctItemArgs, {
               price : "aaa"
            });
            var itemModel = new ItemModel(itemArgsWithNonNumberPrice);

            expect(itemModel.validate()).toBeFalsy();
        });

        it("fails to validate when price is empty", function() {
            var itemArgsWithEmptyPrice = extendWith({}, correctItemArgs, {
                price : ""
            });

            var itemModel = new ItemModel(itemArgsWithEmptyPrice);
            expect(itemModel.validate()).toBeFalsy();
        });

        it("fails to validate when price is greater than threshold", function() {
            var itemArgsWithPriceGreaterThanThreshold = extendWith({}, correctItemArgs, {
                price : "100001"
            });

            var itemModel = new ItemModel(itemArgsWithPriceGreaterThanThreshold);

            expect(itemModel.validate()).toBeFalsy();
        });

    });

    describe("validation", function () {
        var itemValidation, itemForm;

        beforeEach(function () {
            setUpHTMLFixture();
            itemValidation = new ItemValidator(function () {});
        });



        xit("displays errors when there are price validation errors", function () {
            spyOn(itemValidation, 'errorDisplayStrategy');
            itemForm = { price: "aaa"};

            itemValidation.validate(itemForm);

            expect(itemValidation.errorDisplayStrategy).toHaveBeenCalledWith("Price should be a float only");
        });

        it("should return false if the price is more than 10000", function() {
            var invalidPrice = "123456";
            $("#price").val(invalidPrice);

            expect(isPriceValid("#price")).toBeFalsy();
        });
    });
});
  
