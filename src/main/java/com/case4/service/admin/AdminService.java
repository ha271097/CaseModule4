package com.case4.service.admin;

import com.case4.model.User;
import com.case4.repo.IAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService{


    @Autowired
    private IAdminRepo adminRepo;

    @Override
    public Iterable<User> findAll() {
        return adminRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return adminRepo.findById(id);
    }

    @Override
    public void save(User user) {
        adminRepo.save(user);
    }

    @Override
    public void remove(Long id) {
        adminRepo.deleteById(id);
    }
}
