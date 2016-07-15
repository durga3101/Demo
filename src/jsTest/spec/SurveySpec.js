describe("survey pop up", function () {

    survey = new Survey();
    surveyPopUp = new SurveyPopUp();

    it("should pop up if cookie does not exist", function () {
    	$.cookie = jasmine.createSpy('cookie').and.returnValue(false);
        spyOn(surveyPopUp, 'show');

        survey.showSurvey(surveyPopUp);
        expect(surveyPopUp.show).toHaveBeenCalled();
    });

    it("should not pop up if cookie exists", function () {
        $.cookie = jasmine.createSpy('cookie').and.returnValue(true);
        spyOn(surveyPopUp, 'show');

        survey.showSurvey(surveyPopUp);
        expect(surveyPopUp.show).not.toHaveBeenCalled();
    });

    it("should display modal when show function called", function () {
        var elem = document.createElement('div');
        elem.id = "modal";
        document.body.appendChild(elem);

        document.getElementById('modal').style.display = 'none';

        surveyPopUp.show();

        expect(document.getElementById('modal').style.display).toEqual('block');
    });
  
});
