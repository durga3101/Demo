fdescribe("validate credit card details form", function(){
    function setUpHTMLFixture() {
        jasmine.getFixtures().set(' <select id="card_type"  > <option value="VISA">Visa</option><option value="AMEX">AMEX</option><option>Mastercard</option><option>American Express</option></select>\
                                <input id="card_number" type = "text">\
                                <input id="card_ccv" type = "text">\
                                <input id="card_exp_date" type="date"> \
                            ');
    }

    beforeEach(function () {
        setUpHTMLFixture();
    });

    it("should return false when card number contains a letter", function () {
        var invalidCardNumber = "17265dfjh";
        $("#card_number").val(invalidCardNumber);

        expect(isCardFieldValid("#card_number")).toBeFalsy();
    });

    it("should return true when card number contains only numbers", function () {
        var validCardNumber = "17265";
        $("#card_number").val(validCardNumber);

        expect(isCardFieldValid("#card_number")).toBeTruthy();
    });

    it("should return false when CCV contains a letter", function () {
        var invalidCVV = "a129";
        $("#card_ccv").val(invalidCVV);

        expect(isCardFieldValid("#card_ccv")).toBeFalsy();
    });

    it("should return true when CCV contains only numbers", function () {
        var validCVV = "129";
        $("#card_ccv").val(validCVV);
        $("#card_type").val("VISA");

        expect(isCardFieldValid("#card_ccv")).toBeTruthy();
    });

    it("should return false when AMEX CVV is only 3 digits", function () {
        var validCCV = "123";
        $("#card_ccv").val(validCCV);
        $("#card_type").val("AMEX");

        expect(isCardFieldValid("#card_ccv")).toBeFalsy();
    });

    it("should return true when AMEX CVV is 4 digits", function () {
        var validCCV = "1234";
        $("#card_ccv").val(validCCV);
        $("#card_type").val("AMEX");

        expect(isCardFieldValid("#card_ccv")).toBeTruthy();
    });

    it("should return false when Non-AMEX CVV is more than 3 digits", function () {
        var validCCV = "1234";
        $("#card_ccv").val(validCCV);
        $("#card_type").val("VISA");

        expect(isCardFieldValid("#card_ccv")).toBeFalsy();
    });
    it("should return false when date field is blank", function () {
        

        expect(isCardFieldValid("#card_ccv")).toBeTruthy();
    });
    
})