function SurveyPopUp() {
    var modal = document.getElementById('modal');
    var closeButton = document.getElementById('closeButton');
    var surveySubmitButton = document.getElementById('surveySubmitButton');

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
        
        surveySubmitButton.onclick = function(e) {
            var url = "/survey"; 

            $.ajax({
                type: "POST",
                url: url,
                data: $("#survey").serialize(), 
                success: function(data) {
                    alert(data); 
                    console.log(data);
                }
            });
            modal.style.display = 'none';
            e.preventDefault(); 
        }

    }

    function init() {
        addModalCloseListener();
    }

    init();
    
}
