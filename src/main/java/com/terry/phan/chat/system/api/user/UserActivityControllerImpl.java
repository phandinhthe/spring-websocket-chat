package com.terry.phan.chat.system.api.user;

import com.terry.phan.chat.system.util.ActiveUserManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

@RestController
public class UserActivityControllerImpl extends AbstractUserActivityController {

    public UserActivityControllerImpl(ActiveUserManager activeSessionManager) {
        super(activeSessionManager);
    }

    @GetMapping("/rest/active-users-except/{userName}")
    public Set<String> getActiveUsersExceptCurrentUser(@PathVariable String userName) {
        return activeUserManager.getActiveUsersExceptCurrentUser(userName);
    }
}
