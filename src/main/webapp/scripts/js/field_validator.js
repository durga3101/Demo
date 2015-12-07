var validator = {
    isFieldEmpty: function(selector) {
        return $(selector).val().trim() != "";
    },

    displayErrorMessage: function (selector) {
        $(selector).css("display", "inline-block");
    },

    hideErrorMessage: function (selector) {
        $(selector).css("display", "none")
    }

};