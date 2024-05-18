package com.example.leesplezier.security;
import com.example.leesplezier.dtos.UserDto;
import com.example.leesplezier.models.Role;
import com.example.leesplezier.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto userDto = userService.getUser(username);


        String password = userDto.getPassword();

        Role role = userDto.getRole();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.name());


        return new org.springframework.security.core.userdetails.User(username, password, Collections.singleton( grantedAuthority));
    }
}
