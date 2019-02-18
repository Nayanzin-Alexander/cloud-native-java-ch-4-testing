package com.nayanzin.userservice;

import com.nayanzin.userservice.entity.User;
import com.nayanzin.userservice.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/* Embedded in-memory database support is provided for Spring Data JPA for testing. */
@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    /* Convenience class that suports useful subset of proper JPA EnitityManager along
     * with some extra utility methods for testing. */
    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
        User user = new User(55L, "user1", "Jack0", "Frost0", "jfrost0@example.com");
        user.setCreatedAt(123450L);
        user.setLastModified(1234660L);
        user.setId(null);
    //    this.entityManager.persist(user);
    }

    @Test
    public void findUserShouldReturnUser() {
        User actual = userRepository.findUserByUsername("username");
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isGreaterThanOrEqualTo(0L);
        assertThat(actual.getUsername()).isEqualTo("username");
        assertThat(actual.getFirstName()).isEqualTo("first name");
        assertThat(actual.getLastName()).isEqualTo("last name");
        assertThat(actual.getEmail()).isEqualTo("email@email.com");
        assertThat(actual.getCreatedAt()).isNotNull();
        assertThat(actual.getLastModified()).isNull();
    }

    @Test
    public void findUserShouldReturnNull() {
        User actual = userRepository.findUserByUsername("not existent username");
        assertThat(actual).isNull();
    }
}
