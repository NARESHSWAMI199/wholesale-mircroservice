package com.auth.entity;


import com.auth.utils.Util;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Random;

@Entity
@Table(name = "user")
/** this will hit when you call @deleteById functions from jpa */
@SQLDelete(sql = "update user u set u.is_deleted=true where id = ?")
@Where(clause = "is_deleted=false")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable,Comparable<User>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "slug")
    String slug;

    @Column(name = "status")
    Boolean status = Boolean.TRUE;

    @Column(name = "username")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "bio")
    String bio;

    @Column(name = "address_id")
    String addressId;

    @Column(name = "mobile")
    String mobile;

    @Column(name = "profile_pic")
    String profilePic;

    @Column(name = "user_type")
    String userType;

    @Column(name = "created_at")
    Long createdAt = Util.getCurrentTime();

    @Column(name = "updated_at")
    Long updatedAt = Util.getCurrentTime();

    @Column(name = "created_by")
    Integer createdBy;

    @Column(name = "updated_by")
    Integer updatedBy;

    @Column(name = "is_deleted")
    Boolean isDeleted = Boolean.FALSE;



    /** sort according id desc */
    public static Comparator<User> userComparator = new Comparator<User>() {
        @Override
        public int compare(User user1, User user2) {
            return user1.getEmail().compareTo(user2.getEmail());
        }
    };


    @Override
    public int compareTo(User o) {
        return o.id - this.id;
    }
}
