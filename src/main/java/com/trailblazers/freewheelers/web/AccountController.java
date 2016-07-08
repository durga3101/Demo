package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.CountryReader;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static com.trailblazers.freewheelers.model.AccountValidator.verifyInputs;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final String COUNTRIES_FILE_PATH = "src/main/resources/countries.txt";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String COUNTRY = "country";
    public static final String VALIDATION_MESSAGE = "validationMessage";
    AccountService accountService;

    public AccountController() {
        accountService = new AccountServiceImpl();
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {
        BufferedReader  bufferedReader = null;
        try {
            File file = new File(COUNTRIES_FILE_PATH);
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] countries = new CountryReader(bufferedReader).getCountries();
        model.addAttribute(COUNTRY, countries);
        return new ModelAndView("account/create", VALIDATION_MESSAGE, model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {


        Account account = new Account()
                .setEmail_address(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .setAccount_name(request.getParameter("name"))
                .setPhoneNumber(request.getParameter("phoneNumber"))
                .setCountry(request.getParameter("country"))
                .setEnabled(true);

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
