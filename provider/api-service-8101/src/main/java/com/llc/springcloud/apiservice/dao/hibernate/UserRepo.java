package com.llc.springcloud.apiservice.dao.hibernate;

import com.llc.springcloud.common.hibernate.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    Optional<User> findByIdAndUserName(Long id,String userName);
}
