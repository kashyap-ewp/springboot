package com.authenticationemail.demo.services.auth.impl;

import com.authenticationemail.demo.models.Role;
import com.authenticationemail.demo.models.User;
import com.authenticationemail.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SuppressWarnings("unused")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        Optional<User> user = userRepository.findByUsername(username);

        //noinspection SimplifyOptionalCallChains
        if(!user.isPresent()) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        for(Role role : user.get().getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new  org.springframework.security.core.userdetails.User(user.get().getUsername(),user.get().getPassword(),authorities);
    }
}
