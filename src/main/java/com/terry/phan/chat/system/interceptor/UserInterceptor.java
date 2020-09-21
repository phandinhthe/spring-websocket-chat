package com.terry.phan.chat.system.interceptor;

import com.terry.phan.chat.system.dto.ChatMessage;
import com.terry.phan.chat.system.dto.User;
import com.terry.phan.chat.system.util.TimeUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.Optional;

public class UserInterceptor implements ChannelInterceptor {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand command = accessor.getCommand();
        if (notConnect(command)) {
            return message;
        }

        Optional<User> optionalUser = UserInterceptorHelper.extractUser(message);
        optionalUser.ifPresent((user) -> accessor.setUser(user));
        return message;
    }

    private boolean isConnect(StompCommand command) {
        return StompCommand.CONNECT.equals(command);
    }

    private boolean notConnect(StompCommand command) {
        return !isConnect(command);
    }
}
