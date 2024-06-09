package com.KnowNetix.Distributed.AI.Driven.multi.agent.system.serviceImpl;

import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.exception.ErrorCodes;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.model.User;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.repository.UserRepository;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.security.services.UserDetailsImpl;
import com.KnowNetix.Distributed.AI.Driven.multi.agent.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsImpl getUserDetails() {
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  (UserDetailsImpl) principal;
    }

    @Override
    public String getAccessToken() {
        return (String) RequestContextHolder.currentRequestAttributes().getAttribute("accessToken",0);
    }

    @Override
    @Transactional
    public User getUser(Long id) {
        Optional<User> userOptional=userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException(ErrorCodes.USER_NOT_FOUND.getMessage());

        }
        return  userOptional.get();
    }
}
