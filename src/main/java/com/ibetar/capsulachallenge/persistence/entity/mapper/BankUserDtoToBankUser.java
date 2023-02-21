package com.ibetar.capsulachallenge.persistence.entity.mapper;

import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import org.springframework.stereotype.Component;

@Component
public class BankUserDtoToBankUser implements IMapper<BankUserDto, BankUser>{
    @Override
    public BankUser map(BankUserDto in) {
        BankUser bankUser =new BankUser();
        bankUser.setFirstName(in.getFirstName());
        bankUser.setLastName(in.getLastName());
        bankUser.setEmail(in.getEmail());
        bankUser.setPhoneNumber(in.getPhoneNumber());
        return bankUser;
    }
}
