package com.case4.service.user;


import com.case4.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements IUserService, UserDetailsService {

    @Autowired
    private IUserRepo repo;


    @Override
    public com.case4.model.User getUserByName(String name) {
        return repo.findByUsername(name);
    }


    //chuyen doi tuong thuoc lop AppUser sang dt thuoc lop UserDetails
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //lay doi tuong thuoc lop AppUser
        com.case4.model.User user = this.getUserByName(username);
        //lay quyen cua doi tuong do
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) user.getRole());

        //Chuyen doi tuong do sang UserDetail

        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
