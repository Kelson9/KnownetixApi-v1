package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;

import java.util.List;

public interface UserService {
    UserDetailsImpl getUserDetails();

    String getAccessToken();

    User getUser(Long id);

    List<User> getAllUserByEnrollments(Long enrollmentId);

    void updateUserCognitiveState(Long userId, double state);
}
