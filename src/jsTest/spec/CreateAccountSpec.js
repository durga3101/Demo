describe("validate registration form", function () {

    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <input id="fld_email" type="text" > \
                                <input id="fld_name" type = "text">\
            <input id="fld_phoneNumber" type = "text">\
                                               <input type="password" id="fld_password" placeholder="secret password" name="password">\
            <input type="password" id="fld_confirmPassword" placeholder="secret password" name="password">\
            <select id="fld_country" name="country"> <option value="" selected="selected"> </option><option value="Italy"></option>\
                            ');
    }

    beforeEach(function () {
        setUpHTMLFixture();
    });

    describe("Email Validation",function () {
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
    })
   
    fdescribe("Name Validation", function () {
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
    })
    
    describe("Country Validation", function () {
        it("should return true when country is valid", function () {
            var validCountry = 'Italy';
            $("#fld_country").val(validCountry);
    
            expect(isValid("#fld_country")).toBeTruthy();
        });
    
        it("should return false when country is empty", function () {
            var emptyCountry = "";
            $("#fld_country").val(emptyCountry);
    
            expect(isValid("#fld_country")).toBeFalsy();
        });
    
        it("should return true when country is valid", function () {
            var validCountry = 'USA';
            $("#fld_country").val(validCountry);
            expect(isValid("#fld_country")).toBeTruthy();
        });
    })
    
    describe("Password Validation", function () {
        it("should return false if password contains less than 8 characters", function () {
            var invalidPassword = "pass1?";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeFalsy();
        });
        
        it("should return false if password contains more than 20 characters", function () {
            var invalidPassword = "wrongwrongwrongwrongwrong1%";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeFalsy();
        });

        it("should return false if password does not contains number", function () {
            var invalidPassword = "password@";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeFalsy();
        });

        it("should return false if password does not contains a lowerCase letter", function () {
            var invalidPassword = "PASSWORD1@";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeFalsy();
        })
        
        it("should return false if password does not contains a uppercase letter", function () {
            var invalidPassword = "password1&";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeFalsy();
        })

        it("should return false if password does not contains a special character", function () {
            var invalidPassword = "PASSword1";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeFalsy();
        })

        it("should return true if password is valid", function () {
            var invalidPassword = "Password1!";
            $("#fld_password").val(invalidPassword);
            expect(isValid("#fld_password")).toBeTruthy();
        })

        it("should return false if passwords are different", function () {
            var invalidPassword1 = "Password1!";
            var invalidPassword2 = "Password2!";
            $("#fld_password").val(invalidPassword1);
            $("#fld_confirmPassword").val(invalidPassword2);
            expect(isValid("#fld_confirmPassword")).toBeFalsy();
        }) 
        it("should return true if passwords are same", function () {
            var invalidPassword1 = "Password1!";
            var invalidPassword2 = "Password1!";
            $("#fld_password").val(invalidPassword1);
            $("#fld_confirmPassword").val(invalidPassword2);
            expect(isValid("#fld_confirmPassword")).toBeTruthy();
        })
    })
});

