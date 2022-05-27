package com.panilya.authappserver.security;

import com.panilya.authappserver.model.User;
import com.panilya.authappserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public CustomUserDetails(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return CustomUser.create(user);
    }
}
