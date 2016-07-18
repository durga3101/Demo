function SurveyPopUp() {
    var modal = document.getElementById('modal');
    var closeButton = document.getElementById('closeButton');
    var surveySubmitButton = document.getElementById('surveySubmitButton');
    var surveyThankYou = document.getElementById('surveyThankYou');
    var survey = document.getElementById('survey');
    var closeMessageButton = document.getElementById('closeMessageButton');

    this.showSurvey = function() {
        if (!$.cookie("SurveyTaken")) {
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
        closeMessageButton.onclick = function() {
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
                }
            });
            survey.style.display = 'none';
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
