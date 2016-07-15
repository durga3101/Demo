describe("survey pop up", function () {
    var survey;
    var surveyPopUp;

    function setUpHTMLFixture() {
        jasmine
            .getFixtures()
            .set('\
                <div id="modal" style="display: none"> \
                    <button id="modal-close">Close</button> \
                </div>');
    }

    beforeEach(function () {
        setUpHTMLFixture();
        survey = new Survey();
        surveyPopUp = new SurveyPopUp();
    });

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
        surveyPopUp.show();

        expect(document.getElementById('modal').style.display).toEqual('block');
    });
    
    it("should hide modal when click close button" , function () {
        document.getElementById('modal').style.display = 'block';

        document.getElementById('modal-close').click();

        expect(document.getElementById('modal').style.display).toEqual('none');
    })
});
