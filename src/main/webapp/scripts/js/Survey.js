function SurveyPopUp() {
    var modal = document.getElementById('modal');
    var closeButton = document.getElementById('closeButton');
    var surveySubmitButton = document.getElementById('surveySubmitButton');
    var surveyThankYou = document.getElementById('surveyThankYou');
    var survey = document.getElementById('survey');
    var cancel = document.getElementById('cancel');
    var userName = document.getElementById('welcome').innerText;
    
    this.showSurvey = function() {
        if (!$.cookie(userName.substring(8,userName.length-1))) {
            
            this.show();
        }
    };

    this.show = function() {
        modal.style.display = 'block';
    };

    function registerCloseButtonListener() {
        closeButton.onclick = function() {
            modal.style.display = 'none';
        }
        cancel.onclick = function() {
            modal.style.display = 'none';
        }
    }

    function registerSubmitButtonListener(){
        surveySubmitButton.onclick = function(e) {
            var url = "/survey";
            $.ajax({
                type: "POST",
                url: url,
                data: $("#survey").serialize(),
                success: function(data) {
                },
                failure: function(data) {
                }
            });
            survey.style.display = 'none';
            modal.style.display = 'block';
            surveyThankYou.style.display = 'block';

            e.preventDefault();
        }
    }
    


    function init() {
        registerCloseButtonListener();
        registerSubmitButtonListener();
    }

    init();
    
}
