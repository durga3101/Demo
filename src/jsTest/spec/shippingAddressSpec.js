describe("validate addressShipping form", function () {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input type="text" id="fld_street1" placeholder="" name="street1"> \
                                    <input type="text" id="fld_street2" placeholder="" name="street2"> \
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

        expect(isNotNull("#fld_street1")).toBeFalsy();
    });


    it("should return true when street1 is valid", function () {
        var Street1 = "street";
        $("#fld_street1").val(Street1);
        expect(isNotNull("#fld_street1")).toBeTruthy();
    });

    it("should return true when street1 is contains atmost 255 characters", function () {
        var street1 = "examplestreet";
        $("#fld_street1").val(street1);
        expect(isNotNull("#fld_street1")).toBeTruthy();
    });
    it("should return true when street2 is contains atmost 255 characters", function () {
        var street2 = "examplestreet";
        $("#fld_street2").val(street2);
        expect(isNotNull("#fld_street2")).toBeTruthy();
    });

    it("should return false when city is empty", function () {
        var emptyCity = "";
        $("#fld_city").val(emptyCity);

        expect(isNotNull("#fld_city")).toBeFalsy();
    });

    it("should return true when city is valid", function () {
        var city = "city";
        $("#fld_city").val(city);

        expect(isNotNull("#fld_city")).toBeTruthy();
    });


    it("should return true when city contains atmost 100 characters", function () {
        var city = "examplecity";
        $("#fld_city").val(city);
        expect(isNotNull("#fld_city")).toBeTruthy();
    });

    it("should return false when city contains more than 100 characters", function () {
        var city = "examplecityExamplecityExamplecityExamplecityExamplecityexamplecityExamplecityExamplecityExamplecityExamplecity";
        $("#fld_city").val(city);
        expect(isNotNull("#fld_city")).toBeFalsy();
    });

    it("should return false when state is empty", function () {
        var emptyState = "";
        $("#fld_state").val(emptyState);

        expect(isNotNull("#fld_state")).toBeFalsy();
    });

    it("should return true when state is valid", function () {
        var state = "state";
        $("#fld_state").val(state);

        expect(isNotNull("#fld_state")).toBeTruthy();
    });
    
    it("should return true when state is contains atmost 255 characters", function () {
        var state = "examplestate";
        $("#fld_state").val(state);
        expect(isNotNull("#fld_state")).toBeTruthy();
    });
    
    it("should return false when postcode is empty", function () {
        var emptyPostcode = "";
        $("#fld_postcode").val(emptyPostcode);
        expect(isNotNull("#fld_postcode")).toBeFalsy();
    });

    it("should return true when postcode is valid", function () {
        var postcode = "postcode";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeTruthy();
    });
    
    it("should return false when postcode contains lessthan 4 characters", function () {
        var postcode = "522";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeFalsy();
    });
    it("should return true when postcode contains greaterthan 4 characters", function () {
        var postcode = "522426";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeTruthy();
    });
    it("should return false when postcode contains greaterthan 20 characters", function () {
        var postcode = "52242652242652242652242";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeFalsy();
    });
    it("should return true when postcode contains -", function () {
        var postcode = "522-426";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeTruthy();
    });
    it("should return true when postcode contains space", function () {
        var postcode = "522 426";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeTruthy();
    });
    it("should return false when postcode contains Special characters", function () {
        var postcode = "522@426";
        $("#fld_postcode").val(postcode);
        expect(isNotNull("#fld_postcode")).toBeFalsy();
    });
});
