package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountValidator;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;
    AccountValidator accountValidation;

    public AccountController() {
        accountService = new AccountServiceImpl();
        accountValidation =  new AccountValidator();
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public ModelAndView createAccountForm(Model model) {
        return new ModelAndView("account/create", "validationMessage", model);
    }

    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public ModelAndView processCreate(HttpServletRequest request) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");

        Account account = new Account()
                .setEmail_address(email)
                .setPassword(password)
                .setAccount_name(name)
                .setPhoneNumber(phoneNumber)
                .setEnabled(true);

        HashMap errors = accountValidation.verifyInputs(account);
        if(!errors.isEmpty()) {
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
