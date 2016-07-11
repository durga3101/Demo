var fields = ["#card_type",'#card_number', '#card_ccv', "#date"];

function errorSelector(field){
    return field + "_field" + " .text-error";
}
function validateCreditCardDetails() {
    var validate = true;
    fields.forEach(function (field) {
        validate = validate && isCardFieldValid(field);
    });

    console.log("making request");
    var data = new FormData();
    data.append('cc_number', '4111111111111111');
    data.append('csc', '534');
    data.append('expiry', '11-2020');
    data.append('amount', '52.04');

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://ops.freewheelers.bike:5000/authorise', true);
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.onload = function(){
        console.log("i got a response");
        console.log(this.responseText);
    }
    xhr.send(data);
    console.log("did this shit");
    return false;
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
            $("#date_month").val() !== "Month" &&
            $("#date_year").val() !== "Year"
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