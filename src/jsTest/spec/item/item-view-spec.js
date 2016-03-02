describe("ItemView", function() {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(
            '<input id="name" type="text" value="Name"/>' +
            '<input id="price" type="text" value="10.0"/>' +
            '<input id="type" type="text" value="Type"/>' +
            '<input id="description" type="text" value="Desc">' +
            '<input id="quantity" type="text" value="3">' +
            '<div id="err1"></div>' +
            '<div id="err2" style="display: none;"></div>'
        );
    }

    beforeEach(function() {
       setUpHTMLFixture();
    });

    it("model should validate when created from view", function() {
        var model = new ItemView().getModel();

        expect(model.validate()).toBeTruthy();
    });

    it("should hide error", function() {
        new ItemView().hideErrors(['err1']);

        expect($('#err1').is(":hidden")).toBeTruthy();
    });

    it("should show error", function() {
        new ItemView().showErrors(['err2']);

        expect($('#err2').is(":visible")).toBeTruthy();
    });

});