package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountBuilder;
import org.junit.Before;
import org.junit.Test;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class AccountMapperTest extends MapperTestBase {

    private AccountMapper accountMapper;
    private Account account;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        accountMapper = getSqlSession().getMapper(AccountMapper.class);

    }

    @Test
    public void shouldInsertAndGetAccount() throws Exception {

        account = someAccount().setAccountName("Johnny Cash").build();

        accountMapper.insert(account);
        Account fetchedFromDB = accountMapper.getById(account.getAccount_id());

        assertThat(fetchedFromDB.getAccount_name(), is("Johnny Cash"));
    }

    @Test
    public void shouldGetAccountByName() throws Exception {
        account = someAccount().setAccountName("Michael Stipe").build();
        accountMapper.insert(account);

        Account fetchedFromDB = accountMapper.getByName("Michael Stipe");

        assertThat(fetchedFromDB.getAccount_name(), is("Michael Stipe"));
    }

    @Test
    public void shouldUpdateAnExistingAccount() throws Exception {
        Account someAccount = someAccount().setAccountName("Prince").build();
        accountMapper.insert(someAccount);

        someAccount.setAccountName("TAFKAP");
        accountMapper.update(someAccount);

        Account fetched = accountMapper.getById(someAccount.getAccount_id());
        assertThat(fetched.getAccount_name(), is("TAFKAP"));
    }

    @Test
    public void shouldFindAllAccounts() throws Exception {
        int before = accountMapper.getAllAccounts().size();

        accountMapper.insert(someAccount().build());
        accountMapper.insert(someAccount().build());
        accountMapper.insert(someAccount().build());

        assertThat(accountMapper.getAllAccounts().size(), is(before + 3));
    }

    @Test
    public void shouldReturnNullIfAnAccountDoesNotExist() throws Exception {
        assertThat(accountMapper.getByName("Does Not Exist"), is(nullValue()));
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        account = someAccount().build();
        accountMapper.insert(account);
        accountMapper.delete(account);
        Account fetched = accountMapper.getById(account.getAccount_id());

        assertThat(fetched, is(nullValue()));
    }

    @Test
    public void shouldContainCountryInTheAccount() {
        account = someAccount().build();
        accountMapper.insert(account);
        Account fetchedAccount = accountMapper.getById(account.getAccount_id());
        String country = fetchedAccount.getCountry();
        assertEquals(country, account.getCountry());
    }

    @Test
    public void shouldReturnOneWhenCountingAUsersEmail() throws Exception {
        account = someAccount().build();
        accountMapper.insert(account);
        int emailCount = accountMapper.getEmailCount(account.getEmail_address());
        assertEquals(1, emailCount);
    }

    @Test
    public void shouldReturnZeroWhenCountingANonExistingEmail() throws Exception {
        int emailCount = accountMapper.getEmailCount("Idon'texist@email.notreal");
        assertEquals(0, emailCount);
    }

    private AccountBuilder someAccount() {
        return new AccountBuilder()

                .setAccountName("Some Body")
                .setAccountEmailAddress(randomUUID() + "some.body@gmail.com")
                .setAccountCountry("UK")
                .setAccountPhoneNumber("12345")
                .setAccountPassword("V3ry S3cret");
    }

}

