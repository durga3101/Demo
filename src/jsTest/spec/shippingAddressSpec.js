describe("validate addressShipping form", function () {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input type="text" id="fld_street1" placeholder="" name="street1"> \
                                    <input type="text" id="fld_city" placeholder="city" name="city">\
                                    <input type="text" id="fld_state" placeholder="Your state/province" name="state">\
                                    <input type="text" id="fld_postcode" placeholder="555-123456" name="postcode">\
                                ');
    }

    beforeEach(function () {
        setUpHTMLFixture();
    });

    it("should return false when street1 is empty", function () {
        var emptyStreet1 = "";
        $("#fld_street1").val(emptyStreet1);
    
        expect(isValid("#fld_street1")).toBeFalsy();
    });
    
    it("should return true when street1 is valid", function () {
        var Street1 = "street";
        $("#fld_street1").val(Street1);
    
        expect(isValid("#fld_street1")).toBeTruthy();
    });
    it("should return false when city is empty", function () {
        var emptyCity = "";
        $("#fld_city").val(emptyCity);
    
        expect(isValid("#fld_city")).toBeFalsy();
    });
    
    it("should return true when city is valid", function () {
        var city = "city";
        $("#fld_city").val(city);
    
        expect(isValid("#fld_city")).toBeTruthy();
    });
    it("should return false when state is empty", function () {
        var emptyState = "";
        $("#fld_state").val(emptyState);
    
        expect(isValid("#fld_state")).toBeFalsy();
    });
    
    it("should return true when state is valid", function () {
        var state = "state";
        $("#fld_state").val(state);
    
        expect(isValid("#fld_state")).toBeTruthy();
    });
    it("should return false when postcode is empty", function () {
        var emptyPostcode = "";
        $("#fld_postcode").val(emptyPostcode);
    
        expect(isValid("#fld_postcode")).toBeFalsy();
    });
    
    it("should return true when postcode is valid", function () {
        var postcode = "postcode";
        $("#fld_postcode").val(postcode);
    
        expect(isValid("#fld_postcode")).toBeTruthy();
    });
});
