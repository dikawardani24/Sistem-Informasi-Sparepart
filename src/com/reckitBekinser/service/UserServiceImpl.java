package com.reckitBekinser.service;

import com.dika.database.DatabaseServiceImpl;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class UserServiceImpl extends DatabaseServiceImpl<Integer, User>
        implements UserService {

    @Override
    public User findBy(String username) {
        return findByNamedQuery("User.findByUsername", (HashMap<String, Object> parameters) -> {
            parameters.put("username", username);
            return parameters;
        });
    }

    @Override
    public User findBy(Karyawan karyawan) {
        return findByNamedQuery("User.findByIdkaryawan", (HashMap<String, Object> parameters) -> {
            parameters.put("idkaryawan", karyawan.getId());
            return parameters;
        });
    }
    
    @NotNull
    @Override
    protected Class<User> getEntityKClass() {
        return User.class;
    }
}
