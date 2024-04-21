package com.gyungjo.manage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyungjo.manage.dto.UserDto;
import com.gyungjo.manage.entity.User;
import com.gyungjo.manage.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hibernate.collection.spi.PersistentList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 저장 테스트")
    public void t1() throws Exception {
        //given
        UserDto.Save saveRequest = new UserDto.Save();
        saveRequest.setUsername("testUser");
        saveRequest.setPassword("testPassword");
        saveRequest.setEmail("test@example.com");

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saveRequest)));

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value(saveRequest.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(saveRequest.getEmail()));
    }

    @Test
    @DisplayName("유저 저장시 데이터가 비어있으면 저장 안됨")
    public void t1_e1() throws Exception{
        //given
        UserDto.Save saveRequest = new UserDto.Save();
        saveRequest.setUsername("");
        saveRequest.setPassword("testPassword");
        saveRequest.setEmail("test@example.com");

        //when then
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saveRequest)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    @DisplayName("전체 조회 테스트")
    public void t2() throws Exception {
        //given
        List<UserDto.Save> saveRequestList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserDto.Save saveRequest = new UserDto.Save();
            saveRequest.setUsername("testUser" + i);
            saveRequest.setPassword("testPassword" + i);
            saveRequest.setEmail("test@example.com" + i);
            saveRequestList.add(saveRequest);
        }

        //when
        for (UserDto.Save save : saveRequestList) {
            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(save)));
        }

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(saveRequestList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value(saveRequestList.get(0).getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(saveRequestList.get(0).getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value(saveRequestList.get(1).getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email").value(saveRequestList.get(1).getEmail()));
    }
}
