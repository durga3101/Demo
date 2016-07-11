function ItemViewUpdate(itemDOMForm){
    var itemDOMForm = $(itemDOMForm);
    var quantity =itemDOMForm.find(".quantity");
    var price =itemDOMForm.find(".price");

    var INTEGER_PATTERN = /^[0-9]+$/;
    var FLOAT_PATTERN = /^[0-9.]+$/;

    var self = this;
    this.validate = function () {
        var flag = true;
        for(var i = 0;i<quantity.length;i++) {
            itemDOMForm.find("#empty_"+quantity[i].id).hide();
            itemDOMForm.find("#empty_"+price[i].id).hide();

            if (!(INTEGER_PATTERN).test(quantity[i].value)){
                flag = false;
                itemDOMForm.find("#empty_"+quantity[i].id).show();
            }

            if (!(FLOAT_PATTERN).test(price[i].value)){
                flag = false;
                itemDOMForm.find("#empty_"+price[i].id).show();
            }

        }
        return flag;
    }

    itemDOMForm.submit(function(event) {
        if (!self.validate()) {
            event.preventDefault();
        }
    });
}


