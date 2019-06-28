package com.maxkucher.note_taking.security.services;

import com.maxkucher.note_taking.security.SecurityUserDetails;
import com.maxkucher.note_taking.services.UsersInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
    private final UsersInfoService usersInfoService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new SecurityUserDetails(usersInfoService.get(email));
    }
}
