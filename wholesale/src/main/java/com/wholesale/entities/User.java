package com.wholesale.entities;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    Integer userId;
    String slug;
    Boolean status;
    String username;
    String email;
    String firstName;
    String lastName;
    String bio;
    String mobile;
    String profilePic;
    String userType;
    Long createdAt;
    Long updatedAt;
    Integer createdBy;
    Integer updatedBy;

}
