function Modal() {

    var closeButton = document.getElementsByClassName('modal-close')[0];
    this.openModal = function () {
        document.getElementsByClassName('modal-bg')[0].style.display = 'block'
    }

    this.closeModal = function () {
        document.getElementsByClassName('modal-bg')[0].style.display = 'none';
    }
    
    function init() {
        closeButton.onclick = function () {
            document.getElementsByClassName('modal-bg')[0].style.display = 'none';
        }        
    }

    init();
}

