package com.gyungjo.manage.dto;

import com.gyungjo.manage.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Save{
        @NotBlank
        private String username;
        @NotBlank
        private String password;
        @NotBlank
        private String email;
    }

    @Getter
    public static class Info {
        private final Long id;
        private final String username;
        private final String email;

        public Info(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}
