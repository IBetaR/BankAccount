package com.ibetar.capsulachallenge.events;

import com.ibetar.capsulachallenge.persistence.entity.BankUser;
import com.ibetar.capsulachallenge.persistence.entity.dto.BankUserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionCreatedEvent extends Event<BankUserDto>{
}
