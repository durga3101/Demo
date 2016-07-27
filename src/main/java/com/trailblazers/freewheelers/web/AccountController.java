package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.CountryService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import com.trailblazers.freewheelers.service.impl.CountryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.trailblazers.freewheelers.model.AccountValidator.verifyInputs;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String COUNTRY = "country";
    public static final String VALIDATION_MESSAGE = "validationMessage";
    AccountService accountService;
    CountryService countryService;

    public AccountController() {
        accountService = new AccountServiceImpl();
        countryService=new CountryServiceImpl();

    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {
        List<String> countries;
        countries = countryService.getCountries();
        model.addAttribute(COUNTRY, countries);
        return new ModelAndView("account/create", VALIDATION_MESSAGE, model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {
        System.out.println("creating account");

        Account account = new Account(request.getParameter(PASSWORD), true, request.getParameter(EMAIL), request.getParameter(PHONE_NUMBER), request.getParameter(COUNTRY), request.getParameter(NAME));

        return returnStateOfValidation(account);
    }

    private ModelAndView returnStateOfValidation(Account account) {
        HashMap errors = verifyInputs(account);

        if (!errors.isEmpty()) {
            return showErrors(errors);
        }

        try {
            return showSuccess(accountService.createAccount(account));
        } catch (Exception e) {
            return showError();
        }
    }

    private ModelAndView showErrors(Map errors) {
        ModelMap model = new ModelMap();
        model.put("errors", errors);
        return new ModelAndView("account/create", VALIDATION_MESSAGE, model);
    }

    private ModelAndView showError() {
        return new ModelAndView("account/createFailure");
    }

    private ModelAndView showSuccess(Account account) {
        ModelMap model = new ModelMap();
        model.put(NAME, account.getAccount_name());
        return new ModelAndView("account/createSuccess", "postedValues", model);
    }

}
