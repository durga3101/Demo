package com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.AccountRoleMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

/**
 * Created by jmariano on 7/9/16.
 */
public class UpdateDatabasePassword {
    public static void main(String[] args)
    {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
        AccountRoleMapper roleMapper = sqlSession.getMapper(AccountRoleMapper.class);
        List<Account> accountList = mapper.getAllAccounts();
        System.out.println("Dummy class ************"+accountList.size());
        for (Account account: accountList ) {
            String oldPassword = account.getPassword();
            System.out.println("name:"+account.getAccount_name()+"Country::"+account.getCountry()+ "ld password:"+oldPassword);

            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            password = passwordEncoder.encode(password);

            //if(passwordEncoder.ma)
//            if(oldPassword.equals("")){
//                System.out.println("oooooooooooooo\n");
//            }


//            account.setPassword(oldPassword);
            if(account.getCountry() == null)
            {
//                Account newAccount = new Account(account.getPassword(),true,account.getEmail_address(),"2365","UK",account.getAccount_name());
                //AccountRole newAccountRole = new AccountRole();
                //roleMapper.insert();
//                mapper.delete(account);
//                mapper.insert(newAccount);
//                account.setPhoneNumber("123123123");
//
//                account.setCountry("UK");
                mapper.updatePassword(account);
            }
            System.out.println("New password:"+account.getPassword());

            sqlSession.commit();

        }

        System.out.println("HEREE:"+mapper.getByName("AdminCat").getPassword());
//        Account aux = mapper.getByName("AdminCat");
//        mapper.insert(aux);
//        mapper.delete(aux);
        sqlSession.close();

    }
}
