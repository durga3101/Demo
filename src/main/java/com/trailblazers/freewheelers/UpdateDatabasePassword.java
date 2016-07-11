package com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class UpdateDatabasePassword {
    public void updateDatabaseToEncryptPassword()
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
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
