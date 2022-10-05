package com.ibetar.capsulachallenge.service;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.Base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface BaseService <E extends Base, ID extends Serializable>{

    public List<E> findAll() throws Exception;
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public E update(ID id, E entity) throws Exception;
    public boolean delete(ID id) throws  Exception;
    Collection<BankAccount> list(int limit);

}
