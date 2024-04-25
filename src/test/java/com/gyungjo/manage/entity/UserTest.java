package com.gyungjo.manage.entity;


import com.gyungjo.manage.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //jpa 에 필요한 bean만 올리기 때문에 @SpringBootTest에 비해 속도가 빠름
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //실제 DB 사용하기 위한 어노테이션
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User 생성 테스트")
    //todo spring security 적용 후 다시 작성
    public void t1() {
        //given
        String username = "test";
        String password = "123123";
        String email = "a@a.com";
        User user = User.builder().username(username).password(password).email(email).build();

        //when
        userRepository.save(user);

        //then
        List<User> all = userRepository.findAll();
        assertEquals(1, all.size());
        assertNotNull(all.get(0).getId());
        assertEquals(all.get(0).getUsername() , username);
        assertEquals(all.get(0).getPassword() , password);
        assertEquals(all.get(0).getEmail() , email);
    }

    @Test
    @DisplayName("User 비밀번호 변경 테스트")
    //todo spring security 적용 후 다시 작성
    public void t2(){
        //given
        String username = "test";
        String password = "123123";
        String email = "a@a.com";
        User user = User.builder().username(username).password(password).email(email).build();

        //when
        String changePassword = "1231234";
        userRepository.save(user);
        List<User> all = userRepository.findAll();
        all.get(0).changePassword(changePassword);

        //then
        List<User> changed = userRepository.findAll();
        assertEquals(changed.get(0).getPassword() , changePassword);
    }

    @Test
    @DisplayName("생성자, 수정자 테스트 by JPA Auditing")
    public void t3(){
        //given
        String username = "test";
        String password = "1";
        String email = "yong@test.com";
        User user = User.builder().username(username).password(password).email(email).build();
        userRepository.save(user);

        //when
        User findUser = userRepository.findByUsername(user.getUsername());

        //then
        System.out.println("생성자 = " + findUser.getCreatedBy());
        System.out.println("수정자 = " + findUser.getCreatedBy());
    }

}
