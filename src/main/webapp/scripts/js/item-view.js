function ItemView(){
    var itemModelParameters = {
        price : $("#price").val(),
        name : $("#name").val(),
        type : $("#type").val(),
        description : $("#description").val(),
        quantity : $("#quantity").val()
    };

    this.getModel = function(){
        return new ItemModel(itemModelParameters);
    };
}
