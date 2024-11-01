package com.example.devTimesheet.service.impl;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("userDetailServiceImpl")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(Username);
        if (user == null) {
            System.out.println("User not found!" + Username);
            throw new UsernameNotFoundException("User " + Username + " was not found in the database");
        }
        System.out.println("Found user!" + Username);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.get().getUsername(), user.get().getPassword(), getRole(user));
        System.out.println(userDetails);
        return userDetails;
    }

    public Set<SimpleGrantedAuthority> getRole(Optional<User> user) {
        Role role = user.get().getRole();
        return Collections.singleton(new SimpleGrantedAuthority(role.getNameRole()));
    }
}
