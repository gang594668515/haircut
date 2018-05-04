package com.wg.haircut.controller;

import com.github.pagehelper.PageHelper;
import com.wg.haircut.model.User;
import com.wg.haircut.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

//    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize, Model model){
        //开始分页
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = new ArrayList<User>();
        userList = userService.findAllUser(pageNum,pageSize);
        model.addAttribute("users",userList);
        return "/user/list";
    }
}
