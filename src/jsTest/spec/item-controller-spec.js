describe("item", function () {
    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input id="price" type="text">');
    }

    describe("validation", function () {

        beforeEach(function () {
            setUpHTMLFixture();
        });

        it("should return false if the price is more than 10000", function() {
            var invalidPrice = "123456";
            $("#price").val(invalidPrice);

            expect(isPriceValid("#price")).toBeFalsy();
        });
    });
});
  
