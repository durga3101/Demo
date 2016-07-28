describe("ItemViewUpdate", function () {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(
            '<form id="update_item">' +
            '<input id="name" type="text" value="Name"/>' +
            '<input id="price" class="price" type="text" value="10.0"/>' +
            '<input id="type" type="text" value="Type"/>' +
            '<input id="description" type="text" value="Desc">' +
            '<input id="quantity" class="quantity" type="text" value="3">' +
            '</form>'
        );
    }

    var itemViewUpdate;

    beforeEach(function () {
        setUpHTMLFixture();

        var itemForm = $("#update_item");
        itemViewUpdate = new ItemViewUpdate(itemForm);
    });

    it("should return false when quantity is a negative number", function () {
        var invalidQuantity = "-5";
        $("#quantity").val(invalidQuantity);
        expect(itemViewUpdate.validate()).toBeFalsy();
    });

    it("should return true when quantity is a negative number", function () {
        expect(itemViewUpdate.validate()).toBeTruthy();
    });

    it("should return false when price is a negative number", function () {
        var invalidPrice = "-5";
        $("#quantity").val(invalidPrice);
        expect(itemViewUpdate.validate()).toBeFalsy();
    });

    it("should return true when price is a negative number", function () {
        expect(itemViewUpdate.validate()).toBeTruthy();
    });
});
    