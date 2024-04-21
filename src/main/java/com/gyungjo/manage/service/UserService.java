package com.gyungjo.manage.service;

import com.gyungjo.manage.dto.UserDto;
import com.gyungjo.manage.entity.User;
import com.gyungjo.manage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //todo 페이징 추가
    public List<UserDto.Info> findAllUser() {
        return userRepository.findAll().stream().map(UserDto.Info::new).toList();
    }

    public UserDto.Info findUser(Long id){
        return userRepository.findById(id).map(UserDto.Info::new).orElseThrow();
    }

    //todo 시큐리티 적용 후 비밀번호 암호화
    public void saveUser(UserDto.Save save){
        User user = User.builder().email(save.getEmail()).password(save.getPassword()).username(save.getUsername()).build();
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
