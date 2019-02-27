package com.nayanzin.accountservice.repository;

import com.nayanzin.accountservice.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    private Customer customer;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        customer = Customer.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@email.com")
                .account(Account.builder()
                        .username("username")
                        .accountNumber(new AccountNumber("123456789"))
                        .creditCard(CreditCard.builder()
                                .type(CreditCardType.MASTERCARD)
                                .number("1111-1111-1111-1111")
                                .build())
                        .build())
                .build();
        customer = entityManager.persist(customer);
    }

    @Test
    public void findCustomerByIdReturnCustomer() {
        Customer actual = customerRepository.findById(customer.getId())
                .orElse(null);
        assertThat(actual, notNullValue());
        assertThat(actual.getFirstName(), is("firstName"));
        assertThat(actual.getLastName(), is("lastName"));
        assertThat(actual.getEmail(), is("email@email.com"));
    }
}