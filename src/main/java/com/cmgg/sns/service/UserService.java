package com.cmgg.sns.service;

import com.cmgg.sns.exception.SnsApplicationException;
import com.cmgg.sns.model.User;
import com.cmgg.sns.model.entity.UserEntity;
import com.cmgg.sns.repository.UserEntityRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserEntityRepository userEntityRepository;
    // TODO : implement
    public User join(String userName, String password) {

        //회원가입하려는 userName으로 회원가입된 user가 있는지
       userEntityRepository.findByUserName(userName).ifPresent(it -> {
           throw new SnsApplicationException();
       });

        //회원가입 진행 = user를 등록
        UserEntity userEntity = userEntityRepository.save(UserEntity.of(userName, password));
        return User.fromEntity(userEntity );
    }


    // TODO : implement
    public String login(String userName, String password) {
        //회원 가입 여부 체크
        UserEntity userEntity = userEntityRepository.findByUserName(userName)
            .orElseThrow(() -> new SnsApplicationException());

        //비밀번호 체크
        if(!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException();

        }

        //토큰 생성



        return "";
    }


}
