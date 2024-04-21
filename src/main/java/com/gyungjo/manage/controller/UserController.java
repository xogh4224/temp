package com.gyungjo.manage.controller;

import com.gyungjo.manage.dto.UserDto;
import com.gyungjo.manage.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("")
    public List<UserDto.Info> findAllUser(){
        return userService.findAllUser();
    }
    @GetMapping("/{id}")
    public UserDto.Info findUser(@PathVariable Long id){
        return userService.findUser(id);
    }
    @PostMapping("")
    public void saveUser(@RequestBody @Validated UserDto.Save save){
        userService.saveUser(save);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }
}
