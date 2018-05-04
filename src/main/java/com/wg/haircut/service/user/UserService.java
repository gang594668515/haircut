package com.wg.haircut.service.user;

import com.wg.haircut.model.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
}
