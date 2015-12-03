function setUpHTMLFixture() {
    jasmine.getFixtures().set(' <input id="fld_email" type="text">          \
                                <input id="fld_name" type = "text">\
                            ');
}

describe("validate registration form", function() {

    beforeEach(function() {
        setUpHTMLFixture();
    });

    it("should return false when email is invalid", function() {
        var invalidEmail = "gamil";
        $("#fld_email").val(invalidEmail);

        expect(isValidate("#fld_email")).toBeFalsy();
    });

    it("should return false when email contains blank", function() {
        var blankEmail = "   ";
        $("#fld_email").val(blankEmail);

        expect(isValidate("#fld_email")).toBeFalsy();
    });

    it("should return true when email is valid", function() {
        var validEmail = "test@gmail.com";
        $("#fld_email").val(validEmail);

        expect(isValidate("#fld_email")).toBeTruthy();
    });

    it("should return true when name is valid", function() {
        var validName = "Tim";
        $("#fld_name").val(validName);

        expect(isValidate("#fld_name")).toBeTruthy();
    });

    it("should return false when name is empty", function() {
        var emptyName = "";
        $("#fld_name").val(emptyName);

        expect(isValidate("#fld_name")).toBeFalsy();
    });

});

