package com.example.demo.security;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * user detail service
 * used for getting user and roles information from JPA
 * and passing user and role instances for user details
 */
@Service
public class MyUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserName(userName);

        List<Role> roles = roleRepository.findAllRolesByUsersUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found"));

        return new MyUserDetails(user.get(), roles);
    }
}
