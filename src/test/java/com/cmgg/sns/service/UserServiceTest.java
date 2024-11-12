package com.cmgg.sns.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.cmgg.sns.exception.SnsApplicationException;
import com.cmgg.sns.fixture.UserEntityFixture;
import com.cmgg.sns.model.entity.UserEntity;
import com.cmgg.sns.repository.UserEntityRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @MockBean
    private UserEntityRepository userEntityRepository;


    @Test
    void Working_Well_Reistering() {
        String userName = "userName";
        String password = "password";

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(mock(UserEntity.class)));
        when(userEntityRepository.save(any())).thenReturn(Optional.of(mock(UserEntity.class)));

        Assertions.assertDoesNotThrow(() -> userService.join(userName, password));
    }

    @Test
    void Error_Cannot_Login_With_Username_Unregistered() {
        String userName = "userName";
        String password = "password";

        UserEntity fixture = UserEntityFixture.get(userName, password);

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));
        when(userEntityRepository.save(any())).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(SnsApplicationException.class,() -> userService.join(userName, password));
    }

    @Test
    void Login_Work_Well() {
        String userName = "userName";
        String password = "password";

        UserEntity fixture = UserEntityFixture.get(userName, password);

        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture));
        Assertions.assertDoesNotThrow(() -> userService.login(userName, password));
    }

    @Test
    void Error_Wrong_Password() {
        String userName = "userName";
        String password = "password";
        String wrongPassword = "wrongPassword";

        UserEntity fixture = UserEntityFixture.get(userName, password);
        //mocking
        when(userEntityRepository.findByUserName(userName)).thenReturn(Optional.of(fixture ));

        Assertions.assertThrows(SnsApplicationException.class,() -> userService.join(userName, wrongPassword));
    }
}
