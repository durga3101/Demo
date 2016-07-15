function Survey() {
    this.showSurvey = function (popUpWindow) {
        if (!$.cookie("SurveyTaken")) {
            popUpWindow.show();
        }
    }
}

function SurveyPopUp() {
    this.show = function () {
        document.getElementById('modal').style.display = 'block';
    }
}
