package com.cmgg.sns.controller;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.cmgg.sns.controller.request.UserJoinRequest;
import com.cmgg.sns.exception.SnsApplicationException;
import com.cmgg.sns.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void RegisterMember() throws Exception {
      String userName = "userName";
      String password = "password";

      // TODO : Mocking
      when(userService.join(userName, password)).thenReturn(mock(User.class));

      mockMvc.perform(post("/api/v1/users/join")
          .contentType(MediaType.APPLICATION_JSON)
            //TODO : add request body
          .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
      ).andDo(print())
          .andExpect(status().isOk());

    }

    @Test
    public void Error_with_MemberRegistered() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.join(userName, password)).thenReturn("test_token");


        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                //TODO : add request body
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
            ).andDo(print())
            .andExpect(status().isOk());

    }
    @Test
    public void LogIn() throws Exception {
        String userName = "userName";
        String password = "password";


        when(userService.login(userName, password)).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                //TODO : add request body
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
            ).andDo(print())
            .andExpect(status().isOk());

    }

    @Test
    public void Error_with_Unregistered_userName() throws Exception {
        String userName = "userName";
        String password = "password";

        when(userService.login(userName, password)).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                //TODO : add request body
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
            ).andDo(print())
            .andExpect(status().isOk());

    }

    @Test
    public void Error_with_Wrong_Password() throws Exception {
        String userName = "userName";
        String password = "password";


        when(userService.login(userName, password)).thenThrow(new SnsApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                //TODO : add request body
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName, password)))
            ).andDo(print())
            .andExpect(status().isUnauthorized());

    }
}
