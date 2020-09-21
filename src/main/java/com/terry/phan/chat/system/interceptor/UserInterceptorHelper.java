package com.terry.phan.chat.system.interceptor;

import com.terry.phan.chat.system.dto.User;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.*;

public class UserInterceptorHelper {

    public static final String USERNAME = "username";

    public static Optional<User> extractUser(Message<?> message) {
        Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
        Map map = castOrElseNull(raw, Map.class).orElse(null);
        if (Objects.isNull(map)) {
            return Optional.empty();
        }

        LinkedList list = castOrElseNull(map.get(USERNAME), LinkedList.class).orElse(null);
        if (Objects.isNull(list)) {
            return Optional.empty();
        }

        String name = list.get(0).toString();
        return Optional.of(new User(name));
    }

    private static <T> Optional<T> castOrElseNull(Object object, Class<T> clazz) {
        T rs = null;
        try {
            rs = clazz.cast(object);
        } catch (ClassCastException ex) {

        } finally {
            return Optional.ofNullable(rs);
        }
    }
}
