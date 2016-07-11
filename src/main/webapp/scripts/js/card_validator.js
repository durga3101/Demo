var fields = ["#card_type",'#card_number', '#card_ccv', "#date"];

function errorSelector(field){
    return field + "_field" + " .text-error";
}
function validateCreditCardDetails() {
    var validate = false;
    fields.forEach(function (field) {
        validate = isCardFieldValid(field);
    });
    return validate;
}

function isCardFieldValid(selector){

    if(selector === "#card_type"){
        return $(selector).val() !== "NONE";
    }
    if(selector === "#card_number"){
       return isAllNumbers($(selector).val());
    }
    else if(selector === "#card_ccv"){
       return checkCCV($(selector).val());
    }
    else if(selector === "#date"){
        return (
            $("#date_month").val() !== "NONE" &&
            $("#date_year").val() !== "NONE"
        );
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