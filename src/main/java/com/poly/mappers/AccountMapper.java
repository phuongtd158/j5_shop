package com.poly.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.entities.Account;
import com.poly.models.AccountModel;

@Service
public class AccountMapper {

    @Autowired
    private ModelMapper mapper;

    public Account convertToEntity(AccountModel accountModel) {
        return mapper.map(accountModel, Account.class);
    }

    public AccountModel convertToDTO(Account account) {
        return mapper.map(account, AccountModel.class);
    }
}
