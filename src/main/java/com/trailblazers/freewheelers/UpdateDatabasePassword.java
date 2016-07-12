package com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.model.Account;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class UpdateDatabasePassword {
    private AccountMapper mapper;
    private SqlSession sqlSession;

    public UpdateDatabasePassword(AccountMapper mapper, SqlSession sqlSession) {
        this.mapper = mapper;
        this.sqlSession = sqlSession;
    }

    public void updateDatabaseToEncryptPassword()
    {

        List<Account> accountList = mapper.getAllAccounts();
        for (Account account: accountList ) {
            String oldPassword = account.getPassword();
            System.out.println("name:"+account.getAccount_name()+"Country::"+account.getCountry()+ "ld password:"+oldPassword);

            if(account.getCountry() == null)
            {
                mapper.updatePassword(account);
            }
            System.out.println("New password:"+account.getPassword());

            sqlSession.commit();

        }

        sqlSession.close();

    }
}
