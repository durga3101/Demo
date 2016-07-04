describe("validate registration form", function () {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input id="fld_email" type="text" > \
                                <input id="fld_name" type = "text">\
                                <select id="fld_country" name="country"> <option value="" selected="selected"> </option><option value="Italy"></option>\
                            ');
    }

    beforeEach(function () {
        setUpHTMLFixture();
    });

    it("should return false when email is invalid", function () {
        var invalidEmail = "gamil";
        $("#fld_email").val(invalidEmail);

        expect(isValid("#fld_email")).toBeFalsy();
    });

    it("should return false when email contains blank", function () {
        var blankEmail = "   ";
        $("#fld_email").val(blankEmail);

        expect(isValid("#fld_email")).toBeFalsy();
    });

    it("should return true when email is valid", function () {
        var validEmail = "test@gmail.com";
        $("#fld_email").val(validEmail);

        expect(isValid("#fld_email")).toBeTruthy();
    });

    it("should return true when name is valid", function () {
        var validName = "Tim";
        $("#fld_name").val(validName);

        expect(isValid("#fld_name")).toBeTruthy();
    });

    it("should return false when name is empty", function () {
        var emptyName = "";
        $("#fld_name").val(emptyName);

        expect(isValid("#fld_name")).toBeFalsy();
    });

    it("should return true when country is valid", function () {
        var validCountry = "Italy";
        $("#fld_country").val(validCountry);

        expect(isValid("#fld_country")).toBeTruthy();
    });


    it("should return false when country is empty", function() {
        var emptyCountry = "";
        $("#fld_country").val(emptyCountry);

        expect(isValid("#fld_country")).toBeFalsy();
    });

    it("should return true when country is valid", function () {
        var validCountry = "USA";
        $("#fld_country").val(validCountry);
        expect(isValid("#fld_country")).toBeTruthy();
    });
});

