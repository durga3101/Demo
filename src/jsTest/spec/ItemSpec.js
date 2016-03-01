describe("item", function () {
    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input id="price" type="text">          \
                                ');
    }

    describe("ItemModel", function () {

        it("validates when price is a number", function() {
            var itemModel = new ItemModel({
                price: "1"
            });
            expect(itemModel.validate()).toBeTruthy();
        });

        it("fails to validate when price is not a number", function () {
            var itemModel = new ItemModel({
                price: "aaa"
            });

            expect(itemModel.validate()).toBeFalsy();
        });

        it("fails to validade when price is empty", function() {
            var itemModel = new ItemModel({
                price: ""
            });
            expect(itemModel.validate()).toBeFalsy();
        });


    });

    describe("validation", function () {
        var itemValidation, itemForm;

        beforeEach(function () {
            setUpHTMLFixture();
            itemValidation = new ItemValidator(function () {});
        });



        it("displays errors when there are price validation errors", function () {
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
  
