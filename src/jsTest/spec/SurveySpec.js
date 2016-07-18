describe("survey pop up", function () {
    var surveyPopUp;

    function setUpHTMLFixture() {
        jasmine
            .getFixtures()
            .set('\
                <div id="modal" style="display: none"> \
                       <button type="button" class="button" id="closeButton">Close</button>\
                </div>'
            );
    }

    beforeEach(function () {
        setUpHTMLFixture();

        surveyPopUp = new SurveyPopUp();
    });

    it("should pop up if cookie does not exist", function () {
    	$.cookie = jasmine.createSpy('cookie').and.returnValue(false);
        spyOn(surveyPopUp, 'show');

        surveyPopUp.showSurvey();

        expect(surveyPopUp.show).toHaveBeenCalled();
    });

    it("should not pop up if cookie exists", function () {
        $.cookie = jasmine.createSpy('cookie').and.returnValue(true);
        spyOn(surveyPopUp, 'show');

        surveyPopUp.showSurvey();

        expect(surveyPopUp.show).not.toHaveBeenCalled();
    });

    it("should display modal when show function called", function () {
        surveyPopUp.show();

        expect(document.getElementById('modal').style.display).toEqual('block');
    });
    
    it("should hide modal when click close button" , function () {
        document.getElementById('modal').style.display = 'block';

        document.getElementById('closeButton').click();

        expect(document.getElementById('modal').style.display).toEqual('none');
    })
});
