package com.trailblazers.freewheelers.web;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/gateway")
public class GatewayController {

    private String url = "http://ops.freewheelers.bike:5000/authorise";


    @RequestMapping(value = "", method = RequestMethod.POST)
    public String post(@RequestParam(value = "card_number", required = true) String cc_number,
                       @RequestParam(value = "card_ccv", required = true) String csc,
                       @RequestParam(value = "expiry_month", required = true) String expiry_month,
                       @RequestParam(value = "expiry_year", required = true) String expiry_year,
                       @RequestParam(value = "amount", required = true) String amount
    ) {


        System.out.println("PARAMS");
        System.out.println(cc_number);

        String expiry = expiry_month + "-" + expiry_year;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        String body = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                            "<authorisation-request>" +
                                "<cc_number>" + cc_number + "</cc_number>" +
                                    "<csc>" + csc + "</csc>" +
                                    "<expiry>" + expiry + "</expiry>" +
                                "<amount>" + amount + "</amount>" +
                            "</authorisation-request>";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("************************");
        System.out.println(response);


        // TODO: redirect to success/failure view
        return "redirect:/";
    }
}
