package com.terry.phan.chat.system.api.websocket;

import com.terry.phan.chat.system.dto.ChatMessage;
import com.terry.phan.chat.system.util.ActiveUserChangeListener;
import com.terry.phan.chat.system.util.ActiveUserManager;
import com.terry.phan.chat.system.util.TimeUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Set;

public class AbstractWebSocketController implements ActiveUserChangeListener {
    private final SimpMessagingTemplate webSocket;
    private final ActiveUserManager activeUserManager;

    public AbstractWebSocketController(SimpMessagingTemplate webSocket, ActiveUserManager activeUserManager) {
        this.webSocket = webSocket;
        this.activeUserManager = activeUserManager;
    }

    @PostConstruct
    private void init() {
        activeUserManager.registerListener(this);
    }

    @PreDestroy
    private void destroy() {
        activeUserManager.removeListener(this);
    }

    @GetMapping("/sockjs-message")
    public String getWebSocketWithSockJs() {
        return "sockjs-message";
    }

    @MessageMapping("/chat")
    public void send(SimpMessageHeaderAccessor sha, @Payload ChatMessage chatMessage) {
        ChatMessage message = ChatMessage.builder()
                .from(chatMessage.getFrom()).text(chatMessage.getText())
                .recipient(chatMessage.getRecipient()).time(TimeUtils.getCurrentTime())
                .build();

        if (ObjectUtils.anyNull(sha, sha.getUser(), sha.getUser().getName())) {
            throw new RuntimeException("Principal or principal 's name is null!");
        }

        String sender = sha.getUser().getName();
        String recipient = chatMessage.getRecipient();

        if (!sender.equals(recipient)) {
            webSocket.convertAndSendToUser(sender, "/queue/messages", message);
        }
        webSocket.convertAndSendToUser(recipient, "/queue/messages", message);
    }

    @Override
    public void notifyActiveUserChange() {
        Set<String> activeUsers = activeUserManager.getAll();
        webSocket.convertAndSend("/topic/active", activeUsers);
    }
}
