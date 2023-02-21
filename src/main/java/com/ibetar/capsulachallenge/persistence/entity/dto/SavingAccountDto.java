package com.ibetar.capsulachallenge.persistence.entity.dto;

import com.ibetar.capsulachallenge.persistence.entity.AccountType;
import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.Base;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class SavingAccountDto extends Base {
    private String numberAccount;
    private double balance;
    private String bankUsername;
    private BankUser bankUser;
    private AccountType type;
}
