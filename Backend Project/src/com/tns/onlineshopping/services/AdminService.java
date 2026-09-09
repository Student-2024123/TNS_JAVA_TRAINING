package com.tns.onlineshopping.services;

import com.tns.onlineshopping.entities.Admin;
import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private List<Admin> adminList = new ArrayList<>();

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public List<Admin> getAdmins() {
        return adminList;
    }

    public boolean hasAdmin(int userId) {
        return adminList.stream().anyMatch(a -> a.getUserId() == userId);
    }
}
