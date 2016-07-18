describe("survey pop up", function () {
    var surveyPopUp;

    function setUpHTMLFixture() {
        jasmine
            .getFixtures()
            .set('\
                   <li id="welcome" ></li>\
                <div id="modal" style="display: none"> \
                       <button type="submit" class="button" id="surveySubmitButton">Submit</button>\
                       <button type="button" class="button" id="closeButton">Close</button>\
                       <div id="surveyThankYou" style="display: none"></div>\
                       <button id="closeMessageButton">Close</button>\
                       <form:form id="survey" action="/survey" method="post" modelAttribute="survey" ></form:form>\
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
    });
    
    
    it("should hide modal when click closeMessageButton" , function () {
        document.getElementById('modal').style.display = 'block';

        document.getElementById('closeMessageButton').click();

        expect(document.getElementById('modal').style.display).toEqual('none');
    });
    
    it("should hide survey when click submit button" , function () {
        $.ajax = jasmine.createSpy('ajax').and.returnValue(undefined);
    
        document.getElementById('modal').style.display = 'block';
    
        document.getElementById('surveySubmitButton').click();
    
        expect(document.getElementById('survey').style.display).toEqual('none');
    });
    
    it("should show success message in modal when click submit button" , function () {
        $.ajax = jasmine.createSpy('ajax').and.returnValue(undefined);

        document.getElementById('modal').style.display = 'block';

        document.getElementById('surveySubmitButton').click();

        expect(document.getElementById('surveyThankYou').style.display).toEqual('block');
    });
    
    
    
});
