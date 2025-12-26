// CustomUserDetailsService.java
package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
    }
}
