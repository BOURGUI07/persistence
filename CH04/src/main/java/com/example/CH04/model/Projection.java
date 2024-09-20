package com.example.CH04.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class Projection {

    public interface UserSummary{
        String getUserName();
        @Value("#{target.username} #{target.email}")
        String getInfo();
    }


    @Getter
    public static class UsernameOnly{
        private String username;

        public UsernameOnly(String userName) {
            this.username = userName;
        }
    }
}
