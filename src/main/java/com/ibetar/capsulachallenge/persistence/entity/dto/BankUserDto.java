package com.ibetar.capsulachallenge.persistence.entity.dto;

import com.ibetar.capsulachallenge.persistence.entity.Base;
import com.ibetar.capsulachallenge.persistence.entity.IdentityNumberType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class BankUserDto extends Base {

    private String firstName;
    private String lastName;
    //private IdentityNumberType identityNumberType;
    private String identityNumber;
    private String email;
    private String phoneNumber;
}
