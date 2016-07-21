package com.trailblazers.freewheelers.web;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InvoiceControllerTest {

    private InvoiceController invoiceController;
    private String expected;
    private String actual;

    @Before
    public void setUp() throws Exception {
        invoiceController = new InvoiceController();
    }

    @Test
    public void getShouldReturnInvoicePage() throws Exception {
        expected = "invoice";
        actual = invoiceController.get();

        assertEquals(expected, actual);
    }
}
