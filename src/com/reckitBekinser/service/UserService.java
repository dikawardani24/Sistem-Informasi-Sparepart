package com.reckitBekinser.service;

import com.dika.database.DatabaseService;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.model.User;

interface UserService extends DatabaseService<Integer, User> {
    User findBy(String username);

    User findBy(Karyawan karyawan);
}
