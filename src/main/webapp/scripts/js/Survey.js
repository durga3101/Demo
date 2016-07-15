function SurveyPopUp() {
    var modal = document.getElementById('modal');
    var modalCloseButton = document.getElementById('modal-close');

    this.showSurvey = function () {
        if (!$.cookie("SurveyTaken")) {
            this.show();
        }
    };

    this.show = function () {
        modal.style.display = 'block';
    };

    function addModalCloseListener() {
        modalCloseButton.onclick = function() {
            modal.style.display = 'none';
        }
    }

    function init() {
        addModalCloseListener()
    }

    init()
}
