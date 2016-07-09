var fields = ['#card_number', '#card_ccv'];

function validateCreditCardDetails() {
    console.log("validating");
    var validate = true;
    fields.forEach(function (field) {
        console.log("validate:",validate);
        validate = validate && isCardFieldValid(field);
    });
    console.log("**********",validate);
    return false;
}

function isCardFieldValid(selector){

    if(selector === "#card_number"){
       return isAllNumbers($(selector).val());
    }
    else if(selector === "#card_ccv"){
       return checkCCV($(selector).val());
    }
    else{
        return true;
    }

}

function isAllNumbers(input){
    var numberFormat = new RegExp("^[0123456789]+$")
    return numberFormat.test(input);
}

function checkCCV(ccv){
    if(!isAllNumbers(ccv)) return false;
    if($("#card_type").val() === "AMEX") {
        console.log("FOUND AN AMEX")
        return ccv.length === 4;
    }else{

        return ccv.length === 3;
    }
}

function showCardErrorMessage() {

    fields.forEach(function (field) {
        isCardFieldValid(field) ? validator.hideErrorMessage(errorSelector(field)) : validator.displayErrorMessage(errorSelector(field));
    });
}