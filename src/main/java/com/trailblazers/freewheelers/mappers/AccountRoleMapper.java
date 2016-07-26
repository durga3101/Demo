package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Account;
import com.trailblazers.freewheelers.model.AccountRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface AccountRoleMapper {

    @Insert(
        "INSERT INTO account_role (account_name, role, email_address) VALUES (#{account_name}, #{role}, #{email_address})"
    )
    @Options(keyProperty = "role_id", useGeneratedKeys = true)
    void insert(AccountRole accountRole);

    @Select(
            "select * from account_role where account_name = #{account_name} LIMIT 1"
    )
    AccountRole getByAccountName(String loggedInUser);

    @Select(
            "select * from account_role where email_address = #{email_address} LIMIT 1"
    )
    AccountRole getByAccountEmail(String email_address);
}
