var specialCharacters = ['!', '"', '#', '$', '%', '&', '(', ')', '*', '+', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '_', '`', '{', '|', '}', '~'];

var validator = {
    isFieldEmpty: function(value) {
        return  value.trim() === "";
    },

    displayErrorMessage: function (selector) {
        $(selector).css("display", "inline-block");
    },

    hideErrorMessage: function (selector) {
        $(selector).css("display", "none")
    },
    
    validatePassword: function(val) {
    
        if (!(/[0-9]/.test(val))) {
            return false;
        }
        if (!(/[a-z]/.test(val))) {
            return false;
        }
        if (!(/[A-Z]/.test(val))) {
            return false;
        }
        var index;
        for (index = 0; index < specialCharacters.length; index++) {
            if (val.indexOf(specialCharacters[index]) >= 0) {
                break;
            }
        }
        if (index == specialCharacters.length) {
            return false;
        }
    
        var length = val.length;
        return length >= 8 && length <= 20;
    }

};