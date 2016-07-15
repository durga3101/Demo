function Survey() {
    this.showSurvey = function (popUpWindow) {
        if (!$.cookie("SurveyTaken")) {
            popUpWindow.show();
        }
    }
}

function SurveyPopUp() {
    var modal = document.getElementById('modal');
    var modalClose = document.getElementById('modal-close');

    function addModalCloseListener() {
        modalClose.onclick = function() {
            modal.style.display = 'none';
        }
    }

    this.show = function () {
        modal.style.display = 'block';
    };

    function init() {
        addModalCloseListener()
    }

    init()
}
