package com.terry.phan.chat.system.api.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public interface UserConnectAPI {
    String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    String REMOTE_ADDR = "Remote_Addr";

    @PostMapping("/rest/user-connect")
    default String connect(HttpServletRequest request, @ModelAttribute("username") String userName) {
        Function<String, Optional> getOrElseEmpty =
                (header) -> Optional.ofNullable(header).filter(StringUtils::isNotBlank);
        Optional<String> optionalRemoteAddress = getOrElseEmpty.apply(request.getHeader(REMOTE_ADDR));
        
        if (optionalRemoteAddress.isPresent()) {
            return optionalRemoteAddress.get();
        }

        return optionalRemoteAddress
                .or(() -> Optional.ofNullable(request.getHeader(X_FORWARDED_FOR)))
                .filter(StringUtils::isNotBlank)
                .orElse(request.getRemoteAddr());
    }
}
