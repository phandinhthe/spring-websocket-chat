package com.terry.phan.chat.system.api.websocket;

import com.terry.phan.chat.system.util.ActiveUserManager;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController extends AbstractWebSocketController {

    public WebSocketController(SimpMessagingTemplate webSocket, ActiveUserManager activeUserManager) {
        super(webSocket, activeUserManager);
    }
}
