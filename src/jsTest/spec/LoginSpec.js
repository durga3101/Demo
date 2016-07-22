describe("validate login form", function () {
    function setUpHTMLFixture() {
    jasmine.getFixtures().set('<input type="text" id="login_email" name="j_username" placeholder="Username"></td> \ ' +
        '<button type="submit" name="submit">Sign in</button>');
    }
    
    beforeEach(function () {
        setUpHTMLFixture();
    });

    it("should return false when email is invalid", function () {
        var invalidEmail = "gamil";
        $("#login_email").val(invalidEmail);

        expect(isLoginValid("#login_email")).toBeFalsy();
    });
    
})