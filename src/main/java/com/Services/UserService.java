package com.Services;

import com.Model.Role;
import com.Model.User;
import com.Repositories.RoleRepository;
import com.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public boolean newUser(User user)
    {
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 == null)
        {
            System.out.println(user.getPassword()+"nu i cho");
            Role role = new Role(1L, "USER");
            Set<Role> roles = Collections.singleton(role);
            user.setRoles(roles);
            roleRepository.save(role);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public List<User> AllUsers()
    {
        List<User> users = userRepository.findAll();
        return users;
    }
    public boolean dellUser(long id)
    {
        if (userRepository.findById(id).isPresent())
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean redactUser(String username, String password, long id)
    {
        User user = userRepository.findById(id).orElseThrow();
        if (user!=null)
        {
            user.setUsername(username);
            user.setPassword(password);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    public boolean findUser(User user)
    {
        User user1 = userRepository.findByUsername(user.getUsername());
        if(user1!=null)
        {
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
