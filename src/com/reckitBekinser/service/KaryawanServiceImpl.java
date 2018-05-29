package com.reckitBekinser.service;

import com.dika.database.DatabaseServiceImpl;
import com.reckitBekinser.model.Karyawan;
import org.jetbrains.annotations.NotNull;

public class KaryawanServiceImpl extends DatabaseServiceImpl<Integer, Karyawan>
        implements KaryawanService {

    @NotNull
    @Override
    protected Class<Karyawan> getEntityKClass() {
        return Karyawan.class;
    }

    @Override
    public Karyawan findBy(String noKtp) {
        return findByNamedQuery("Karyawan.findByNoKtp", parameters -> {
            parameters.put("noKtp", noKtp);
            return parameters;
        });
    }
}
