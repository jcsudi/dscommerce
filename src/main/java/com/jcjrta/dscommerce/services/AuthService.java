package com.jcjrta.dscommerce.services;

import com.jcjrta.dscommerce.entities.User;
import com.jcjrta.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validate(long userId){
        User me = userService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)){
            throw new ForbiddenException("Acesso negado");
        }
    }
}
