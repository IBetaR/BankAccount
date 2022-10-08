package com.ibetar.capsulachallenge.persistence.entity.dto;

import com.ibetar.capsulachallenge.persistence.entity.Base;
import io.swagger.annotations.ApiModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class BankAccountDTO extends Base {

    private String numberAccount;
    private double balance;
    private String bankUsername;
}
