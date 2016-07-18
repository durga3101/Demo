function SurveyPopUp() {
    var modal = document.getElementById('modal');
    var closeButton = document.getElementById('closeButton');
    // var surveySubmitButton = document.getElementById('surveySubmitButton');

    this.showSurvey = function() {
        if (!$.cookie("SurveyTaken")) {
            this.show();
        }
    };

    this.show = function() {
        modal.style.display = 'block';
    };

    function addModalCloseListener() {
        closeButton.onclick = function() {
            modal.style.display = 'none';
        }
        // surveySubmitButton.onclick = function() {
        //     modal.style.display = 'none';
        // }
    }

    function init() {
        addModalCloseListener()
    }

    init()
}
