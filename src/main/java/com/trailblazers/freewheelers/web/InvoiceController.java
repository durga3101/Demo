package com.trailblazers.freewheelers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    private static final String INVOICE = "invoice";

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return INVOICE;
    }
}
