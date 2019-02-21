package com.nayanzin.userservice;

import com.nayanzin.userservice.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class UserJsonTest {

    private User user;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JacksonTester<User> json;

    @Value("classpath:com/nayanzin/userservice/user.json")
    private Resource userJson;

    @Before
    public void setUp() {
        User user = new User(0L, "user", "Jack", "Frost", "jfrost@example.com");
        user.setCreatedAt(12345L);
        user.setLastModified(123466L);

        this.user = user;
    }

    @Test
    public void deserializeJson() throws Exception {
        assertThat(this.json.read(userJson)).isEqualTo(user);
    }

    @Test
    public void serializeJson() throws Exception {
        assertThat(this.json.write(user)).isEqualTo("user.json");
        assertThat(this.json.write(user)).isEqualToJson(userJson);
        assertThat(this.json.write(user)).hasJsonPathStringValue("@.username");

        JsonContent<User> jsonContent = this.json.write(user);
        assertThat(jsonContent) .extractingJsonPathStringValue("@.username").isEqualTo("user");
    }
}