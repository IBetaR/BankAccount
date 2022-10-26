package com.ibetar.capsulachallenge.persistence.entity.dto;

import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class CurrentAccountDto {
    private String numberAccount;
    private double balance;
    private String bankUsername;
    private BankUser bankUser;
}
