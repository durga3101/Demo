package com.trailblazers.freewheelers.web;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountValidation;
import com.trailblazers.freewheelers.service.AccountService;
import com.trailblazers.freewheelers.service.ServiceResult;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountControllerTest {

    private AccountController accountController;

    @Mock
    private AccountService accountService;
    @Mock
    private AccountValidation accountValidation;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        accountController = new AccountController();
        accountController.accountService = accountService;
        accountController.accountValidation = accountValidation;
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
        Account account = new Account();
        account.setAccount_name("john smith");
        ServiceResult<Account> success = new ServiceResult<Account>(account);
        when(accountValidation.verifyInputs(any(Account.class))).thenReturn(new HashMap<String, String>());
        when(accountService.createAccount(any(Account.class))).thenReturn(success);

        ModelAndView createView = accountController.processCreate(mock(HttpServletRequest.class));

        ModelMap model = new ModelMap();
        model.put("name", "john smith");
        ExtendedModelMap expectedModel = new ExtendedModelMap();
        expectedModel.put("postedValues", model);
        assertThat(createView.getViewName(), is("account/createSuccess"));
        assertThat(createView.getModel(), is(expectedModel.asMap()));
    }

    @Test
    public void shouldCreateAnAccountFromTheHttpRequest() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("email")).thenReturn("email@fake.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("name")).thenReturn("john smith");
        when(request.getParameter("phoneNumber")).thenReturn("123456789");

        accountController.processCreate(request);

        ArgumentCaptor<Account> captor = ArgumentCaptor.forClass(Account.class);
        verify(accountService).createAccount(captor.capture());

        Account account = captor.getValue();
        assertThat(account.getEmail_address(), is("email@fake.com"));
        assertThat(account.getPassword(), is("password"));
        assertThat(account.getAccount_name(), is("john smith"));
        assertThat(account.getPhoneNumber(), is("123456789"));
        assertThat(account.isEnabled(), is(true));
    }

    @Test
    public void accountValidationFailureShouldShowError() throws Exception {
        HashMap<String, String> errors = new HashMap<String, String>();
        errors.put("some key", "some error message");
        when(accountValidation.verifyInputs(any(Account.class))).thenReturn(errors);

        ModelAndView createView = accountController.processCreate(mock(HttpServletRequest.class));

        ModelMap model = new ModelMap();
        model.put("errors", errors);
        ExtendedModelMap expectedModel = new ExtendedModelMap();
        expectedModel.put("validationMessage", model);
        assertThat(createView.getViewName(), is("account/create"));
        assertThat(createView.getModel(), is(expectedModel.asMap()));
    }

    @Test
    public void accountCreationExceptionShouldShowError() throws Exception {
        when(accountValidation.verifyInputs(any(Account.class))).thenReturn(new HashMap<String, String>());
        when(accountService.createAccount(any(Account.class))).thenThrow(new RuntimeException("validation errors"));

        ModelAndView createView = accountController.processCreate(mock(HttpServletRequest.class));

        assertThat(createView.getViewName(), is("account/createFailure"));
    }

    @Test
    public void shouldNotCallServiceWhenThereAreValidationErrors() throws IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");
        when(request.getParameter("name")).thenReturn("");
        when(request.getParameter("phoneNumber")).thenReturn("");

        accountController.processCreate(request);

        verify(accountService, never()).createAccount(new Account());
    }
}
