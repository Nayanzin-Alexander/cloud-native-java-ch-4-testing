package com.nayanzin.accountservice.repository;

import com.nayanzin.accountservice.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
}
