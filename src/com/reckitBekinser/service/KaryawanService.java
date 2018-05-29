package com.reckitBekinser.service;

import com.dika.database.DatabaseService;
import com.reckitBekinser.model.Karyawan;

interface KaryawanService extends DatabaseService<Integer, Karyawan> {
    Karyawan findBy(String noKtp);
}
