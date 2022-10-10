package com.ibetar.capsulachallenge.persistence.repository;

import com.ibetar.capsulachallenge.persistence.entity.BankAccount;
import com.ibetar.capsulachallenge.persistence.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E , ID> {
}
