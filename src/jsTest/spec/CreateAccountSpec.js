describe("validate registration form", function() {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input id="fld_email" type="text" >          \
                                <input id="fld_name" type = "text">\
                            ');
    }

    beforeEach(function() {
        setUpHTMLFixture();
    });

    it("should return false when email is invalid", function() {
        var invalidEmail = "gamil";
        $("#fld_email").val(invalidEmail);

        expect(isValid("#fld_email")).toBeFalsy();
    });

    it("should return false when email contains blank", function() {
        var blankEmail = "   ";
        $("#fld_email").val(blankEmail);

        expect(isValid("#fld_email")).toBeFalsy();
    });

    it("should return true when email is valid", function() {
        var validEmail = "test@gmail.com";
        $("#fld_email").val(validEmail);

        expect(isValid("#fld_email")).toBeTruthy();
    });

    it("should return true when name is valid", function() {
        var validName = "Tim";
        $("#fld_name").val(validName);

        expect(isValid("#fld_name")).toBeTruthy();
    });

    it("should return false when name is empty", function() {
        var emptyName = "";
        $("#fld_name").val(emptyName);

        expect(isValid("#fld_name")).toBeFalsy();
    });

});

