describe("survey pop up", function () {
    var modal
    function setUpHTMLFixture() {
        jasmine
            .getFixtures()
            .set('\
                <div class="modal-bg" style="display: none"> \
                   <div class="modal-content"> \
                        Some modal content here \
                        <button type="button" class="modal-close">Close modal</button> \
                   </div> \
                </div>'
            );
    }

    beforeEach(function() {
        setUpHTMLFixture();
        modal = new Modal()
        
    })

    describe("open modal using button", function () {
        it("should open the modal when open modal button is clicked", function() {
            modal.openModal();
            expect(document.getElementsByClassName('modal-bg')[0].style.display).toBe('block');
        })
    })
    describe("close modal using button", function () {
        it("should close the modal when close modal button is clicked", function() {
            document.getElementsByClassName('modal-bg')[0].style.display = "block"
            document.getElementsByClassName('modal-close')[0].click();
            expect(document.getElementsByClassName('modal-bg')[0].style.display).toBe('none');
        })
    })
});

    // write function for opening modal
    // bind that function to modal-open button (setup)
    // test that clicking on modal-open changes modal-bg display to block

