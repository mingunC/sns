package com.cmgg.sns.controller.response;

import com.cmgg.sns.model.User;
import com.cmgg.sns.model.UserRole;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJoinResponse {

    private Integer id;
    private String userName;
    private UserRole role;


    public static UserJoinResponse fromUser(User user) {
        return new UserJoinResponse(
            user.getId(),
            user,getUserName(),
            user.getUserRole()
        )
    }

}
