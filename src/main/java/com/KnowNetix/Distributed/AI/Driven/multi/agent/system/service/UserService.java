package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;

public interface UserService {
    UserDetailsImpl getUserDetails();

    String getAccessToken();

    User getUser(Long id);
}
