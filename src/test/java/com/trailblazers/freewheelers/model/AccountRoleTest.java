package com.trailblazers.freewheelers.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AccountRoleTest {

    private AccountRole accountRole;

    @Before
    public void setUp() throws Exception {
        accountRole = new AccountRole();
    }

    @Test
    public void getRole_IdshouldReturnNullWhenRole_IdIsNotSetUp() throws Exception {
        assertNull(accountRole.getRole_id());
    }

    @Test
    public void getRoleShouldReturnUserWhenRoleIsSetUpAsUser() {
        accountRole = accountRole.setRole("user");
        String expected = "user";

        String actual = accountRole.getRole();

        assertEquals(expected, actual);
    }

}