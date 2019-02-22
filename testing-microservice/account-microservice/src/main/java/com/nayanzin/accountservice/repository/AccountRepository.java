package com.nayanzin.accountservice.repository;

import com.nayanzin.accountservice.entity.Account;
import com.nayanzin.accountservice.entity.AccountNumber;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    List<Account> findAccountByUsername(@Param("username") String username);

    Account findByAccountNumber(@Param("accountNumber") AccountNumber accountNumber);
}
