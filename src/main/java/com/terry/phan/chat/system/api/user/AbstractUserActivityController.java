package com.terry.phan.chat.system.api.user;

import com.terry.phan.chat.system.util.ActiveUserManager;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractUserActivityController implements UserConnectAPI, UserDisconnectAPI {
    protected ActiveUserManager activeUserManager;

    public AbstractUserActivityController(ActiveUserManager activeUserManager) {
        this.activeUserManager = activeUserManager;
    }

    public String connect(HttpServletRequest request, String userName) {
        String remoteAddress = UserConnectAPI.super.connect(request, userName);
        activeUserManager.add(userName, remoteAddress);
        return remoteAddress;
    }

    public String disconnect(String userName) {
        activeUserManager.remove(userName);
        return UserDisconnectAPI.super.disconnect(userName);
    }
}
