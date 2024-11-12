package com.cmgg.sns.model;

import com.cmgg.sns.model.entity.UserEntity;
import java.security.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {


    private Integer id;
    private String userName;
    private String password;
    private UserRole userRole;
    private Timestamp registeredAt;
    private Timestamp updatedAt;
    private Timestamp deletedAt;

    public static User fromEntity(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getUserName(),
            entity.getPassword(),
            entity.getRole(),
            entity.getRegisteredAt(),
            entity.getUpdatedAt(),
            entity.getDeletedAt()
        );
    }

}
