package com.auth.respositories;

import com.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /** find user by id */
/*    @Query(value = "Select u from User u where u.slug=:slug")
    public User getUserBySlug(@Param("slug") String slug);*/

    /** Custom finder methods. they follow a pattern so jpa can understand this */
    public User findBySlug(String slug);
}
