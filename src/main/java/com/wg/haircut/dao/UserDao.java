package com.wg.haircut.dao;

import com.wg.haircut.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    int insert(User record);


    List<User> selectUsers();
}
