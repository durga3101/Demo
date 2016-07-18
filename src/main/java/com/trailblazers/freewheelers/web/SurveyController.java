package com.trailblazers.freewheelers.web;


import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.NpsReport;
import com.trailblazers.freewheelers.model.SurveyComments;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.SurveyService;
import com.trailblazers.freewheelers.web.forms.SurveyEntryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.ws.RequestWrapper;
import java.security.Principal;

@Controller
@RequestMapping("/survey")
public class SurveyController {

    private static final int ONE_DAY = 86400;
    @Autowired
    private SurveyService surveyService;
    @Autowired
    private AccountService accountService;

    @ModelAttribute("survey")
    public SurveyEntryForm getSurveyForm() {
        return new SurveyEntryForm();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String get(HttpServletResponse response) {
        Cookie cookie = new Cookie("SurveyTaken", "true");
        cookie.setMaxAge(ONE_DAY);
        response.addCookie(cookie);
        return "survey/form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String  post(Principal principal,
                      @ModelAttribute("survey")
                             @Valid
                             SurveyEntryForm surveyEntryForm,
                      BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "error";
        }

        Cookie cookie = new Cookie(principal.getName(), "true");
        cookie.setMaxAge(ONE_DAY);
        response.addCookie(cookie);

        String username = principal.getName();
        Account account = accountService.getAccountIdByName(username);
        surveyService.submitSurvey(account.getAccount_id(), surveyEntryForm.surveyEntry());
        return "success";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView getReport() {
        NpsReport npsReport = surveyService.generateNpsReport();
        SurveyComments surveyComments = surveyService.getSurveyComments();
        ModelMap model = new ModelMap();
        model.put("npsReport", npsReport);
        model.put("surveyComments", surveyComments);
        return new ModelAndView("survey/report", model);
    }

//    private ModelAndView showValidationError() {
//        ModelMap model = new ModelMap();
//        model.addAttribute("mandatoryFieldMissing", true);
//        return new ModelAndView("survey/form", "validation", model);
//    }

}
