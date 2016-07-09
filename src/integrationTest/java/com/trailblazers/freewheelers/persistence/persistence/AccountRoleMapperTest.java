package com.trailblazers.freewheelers.persistence.persistence;

import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.model.AccountRole;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class AccountRoleMapperTest extends MapperTestBase {

    private AccountRoleMapper accountRoleMapper;
    private AccountRole accountRole;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        accountRoleMapper = getSqlSession().getMapper(AccountRoleMapper.class);
        accountRole = new AccountRole();
        accountRole.setAccount_name("Some Name");
        accountRole.setRole("Some Role");
    }

    @Test
    public void shouldInsertAnAccountRole() throws Exception {


        accountRoleMapper.insert(accountRole);

        assertThat(accountRole.getRole_id(), is(not(nullValue())));
    }

    @Test
    public void shouldReturnAccount() throws Exception {
        accountRoleMapper.insert(accountRole);
        accountRole = accountRoleMapper.get("Some Name");
        assertEquals("Some Role", accountRole.getRole());

    }
}
