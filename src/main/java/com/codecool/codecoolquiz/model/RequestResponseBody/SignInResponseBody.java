package com.codecool.codecoolquiz.model.RequestResponseBody;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SignInResponseBody {
    private String username;
    private List<String> roles;
    private Integer userId;
    private Long exp;

    public SignInResponseBody(String username, List<String> roles, Integer id, Long cookieMaxAgeMinutes) {
        this.username = username;
        this.roles = roles;
        this.userId = id;
        this.exp =  System.currentTimeMillis() + 1000 * 60 * cookieMaxAgeMinutes;
    }
}
