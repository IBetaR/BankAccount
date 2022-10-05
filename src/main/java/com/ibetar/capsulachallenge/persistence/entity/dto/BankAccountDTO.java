package com.ibetar.capsulachallenge.persistence.entity.dto;

import com.ibetar.capsulachallenge.persistence.entity.Base;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountDTO extends Base {

    private String numberAccount;
    private double balance;
    private String bankUsername;
}
