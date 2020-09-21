package com.terry.phan.chat.system.api.user;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserDisconnectAPI {
    @PostMapping("/rest/user-disconnect")
    default String disconnect(@ModelAttribute("username") String userName) {
        return "disconnected";
    }
}
