package com.cmgg.sns.controller;

import com.cmgg.sns.controller.request.UserJoinRequest;
import com.cmgg.sns.controller.response.Response;
import com.cmgg.sns.controller.response.UserJoinResponse;
import com.cmgg.sns.model.User;
import com.cmgg.sns.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/users ")
@RequiredArgsConstructor
public class userController {

    private final UserService userService;

    //TODO : implement
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {

        User user = userService.join(request.getUserName(), request.getPassword());
        UserJoinResponse response = UserJoinResponse.fromUser(user);
        return Response.success(UserJoinResponse.fromUser(user));
    }
}
