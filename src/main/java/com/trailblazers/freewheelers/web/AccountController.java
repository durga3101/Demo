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

    AccountService accountService;

    public AccountController() {
        accountService = new AccountServiceImpl();
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {
        BufferedReader  bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("/Users/prajaktadesai/TWU50/twu50proj2/src/main/resources/countries.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] countries = new CountryReader(bufferedReader).getCountries();
        model.addAttribute("country", countries);
        return new ModelAndView("account/create", "validationMessage", model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        String country = request.getParameter("country");

        Account account = new Account()
                .setEmail_address(email)
                .setPassword(password)
                .setAccount_name(name)
                .setPhoneNumber(phoneNumber)
                .setCountry(country)
                .setEnabled(true);

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
        return new ModelAndView("account/create", "validationMessage", model);
    }

    private ModelAndView showError() {
        return new ModelAndView("account/createFailure");
    }

    private ModelAndView showSuccess(Account account) {
        ModelMap model = new ModelMap();
        model.put("name", account.getAccount_name());
        return new ModelAndView("account/createSuccess", "postedValues", model);
    }

}
