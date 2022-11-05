package com.vincenzoracca.localstack.it;

import com.vincenzoracca.localstack.dao.UserDao;
import com.vincenzoracca.localstack.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(LocalStackConfiguration.class)
@ActiveProfiles("test")
class UserDaoTests {

    @Autowired
    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao.deleteAll();
    }


    @Test
    void saveNewElementTest() {
        var userOne = new User(null, "Vincenzo", "Racca");

        userDao.save(userOne);

        List<User> retrievedUsers = userDao.findAll();
        assertThat(retrievedUsers).hasSize(1);
        assertThat(retrievedUsers.get(0)).isEqualTo(userOne);
    }

    @Test
    void saveAnExistingElementTest() {
        var userOne = new User(null, "Vincenzo", "Racca");

        userDao.save(userOne);

        List<User> retrievedUsers = userDao.findAll();
        assertThat(retrievedUsers).hasSize(1);
        assertThat(retrievedUsers.get(0)).isEqualTo(userOne);
        assertThat(retrievedUsers.get(0).getFirstname()).isEqualTo("Vincenzo");

        userOne.setFirstname("Enzo");
        userDao.save(userOne);

        retrievedUsers = userDao.findAll();
        assertThat(retrievedUsers).hasSize(1);
        assertThat(retrievedUsers.get(0)).isEqualTo(userOne);
        assertThat(retrievedUsers.get(0).getFirstname()).isEqualTo("Enzo");
    }

    @Test
    void deleteTest() {
        var userOne = new User(null, "Vincenzo", "Racca");

        userDao.save(userOne);
        String id = userOne.getId();
        assertThat(id).isNotNull();

        Optional<User> retrievedUser = userDao.findById(id);
        assertThat(retrievedUser.isPresent()).isTrue();
        assertThat(retrievedUser.get()).isEqualTo(userOne);

        userDao.delete(userOne);
        Optional<User> userNotRetrieved = userDao.findById(id);
        assertThat(userNotRetrieved.isPresent()).isFalse();

    }
}
