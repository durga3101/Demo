package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountMapper {

    @Insert(
        "INSERT INTO account (account_name, email_address, password, phone_number, enabled ,country) " +
        "VALUES (#{account_name}, #{email_address}, #{password}, #{phoneNumber}, #{enabled} ,#{country})"
    )
    @Options(keyProperty = "account_id", useGeneratedKeys = true)
    Integer insert(Account account);

    @Select(
        "SELECT account_id, account_name, email_address, password, phone_number, enabled ,country " +
        "FROM account " +
        "WHERE account_id = #{account_id}"
    )
    Account getById(Long account_id);

    @Select(
        "SELECT account_id, account_name, email_address, password, phone_number, enabled , country " +
        "FROM account " +
        "WHERE account_name = #{account_name} " +
        "LIMIT 1"
    )
    Account getByName(String account_name);

    @Select(
            "SELECT count(email_address) FROM account WHERE email_address = #{email}"
    )
    int getEmailCount(String email);

    @Update(
        "UPDATE account " +
        "SET account_name=#{account_name}, email_address=#{email_address}, phone_number=#{phoneNumber}, enabled=#{enabled} " +
        "WHERE account_id=#{account_id}"
    )
    void update(Account account);

    @Select(
        "SELECT account_id, account_name, email_address, password, phone_number, enabled FROM account"
    )
    @Results(value = {
            @Result(property="account_id"),
            @Result(property="account_name"),
            @Result(property="email_address"),
            @Result(property="password"),
            @Result(property="phoneNumber", column="phone_number"),
            @Result(property="enabled"),
            @Result(property = "country")
    })
    List<Account> getAllAccounts();

    @Delete(
        "DELETE FROM account WHERE account_id = #{account_id}"
    )
    @Options(flushCache = true)
    void delete(Account account);


}
