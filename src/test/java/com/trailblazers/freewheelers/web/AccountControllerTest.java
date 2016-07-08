package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountBuilder;
import com.trailblazers.freewheelers.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountControllerTest {

    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        accountController = new AccountController();
        accountController.accountService = accountService;
    }

    @Test
    public void shouldShowTheCreateAccountForm() throws Exception {
        ExtendedModelMap model = new ExtendedModelMap();

        ModelAndView accountForm = accountController.createAccountForm(model);

        ExtendedModelMap expectedModel = new ExtendedModelMap();
        expectedModel.put("validationMessage", model);
        assertThat(accountForm.getViewName(), is("account/create"));
        assertThat(accountForm.getModel(), is(expectedModel.asMap()));
    }

    @Test
    public void successfulAccountCreationShouldShowSuccess() throws Exception {

        Account account = new AccountBuilder().setAccountName("john smith").build();

        HttpServletRequest requestWithoutError = getValidHttpServletRequest();

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        ModelAndView createView = accountController.processCreate(requestWithoutError);

        ModelMap model = new ModelMap();
        model.put("name", "john smith");
        ExtendedModelMap expectedModel = new ExtendedModelMap();
        expectedModel.put("postedValues", model);
        assertThat(createView.getViewName(), is("account/createSuccess"));
        assertThat(createView.getModel(), is(expectedModel.asMap()));
    }

    @Test
    public void shouldCreateAnAccountFromTheHttpRequest() throws Exception {
        HttpServletRequest request = getValidHttpServletRequest();

        accountController.processCreate(request);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(accountService).createAccount(captor.capture());

        Account account = captor.getValue();
        assertThat(account.getEmail_address(), is("email@fake.com"));
        assertThat(account.getPassword(), is("password"));
        assertThat(account.getAccount_name(), is("john smith"));
        assertThat(account.getPhoneNumber(), is("123456789"));
        assertThat(account.getCountry(),is("India"));
        assertThat(account.isEnabled(), is(true));
    }

    @Test
    public void accountValidationFailureShouldShowError() throws Exception {
        HashMap<String, String> errors = new HashMap<String, String>();
        errors.put("email", "Must enter a valid email!");

        HttpServletRequest requestWithError = getInvalidHttpServletRequest();

        ModelAndView createView = accountController.processCreate(requestWithError);

        ModelMap model = new ModelMap();
        model.put("errors", errors);
        ExtendedModelMap expectedModel = new ExtendedModelMap();
        expectedModel.put("validationMessage", model);
        assertThat(createView.getViewName(), is("account/create"));
        assertThat(createView.getModel(), is(expectedModel.asMap()));
    }

    @Test
    public void accountCreationExceptionShouldShowError() throws Exception {
        HttpServletRequest requestWithoutError = getValidHttpServletRequest();
        when(accountService.createAccount(any(Account.class))).thenThrow(new RuntimeException("validation errors"));

        ModelAndView createView = accountController.processCreate(requestWithoutError);

        assertThat(createView.getViewName(), is("account/createFailure"));

    }

    @Test
    public void shouldNotCallServiceWhenThereAreValidationErrors() throws IOException {

        HttpServletRequest request = getInvalidHttpServletRequest();

        accountController.processCreate(request);

        verify(accountService, never()).createAccount(new Account());
    }

    private HttpServletRequest getInvalidHttpServletRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("example");
        when(request.getParameter("name")).thenReturn("example");
        when(request.getParameter("phoneNumber")).thenReturn("1234567890");
        when(request.getParameter("country")).thenReturn("India");

        return request;
    }

    private HttpServletRequest getValidHttpServletRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("email")).thenReturn("email@fake.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("john smith");
        when(request.getParameter("phoneNumber")).thenReturn("123456789");
        when(request.getParameter("country")).thenReturn("India");



        return request;
    }

}
