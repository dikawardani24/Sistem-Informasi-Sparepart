package com.reckitBekinser.tableModel;

import com.alee.laf.table.WebTable;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.model.User;

public final class UserTableModel extends EntityTableModel<User> {
    public UserTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID", "Username", "Karyawan", "Password");
    }

    @Override
    protected void onInsertNewRow(User user) {
        addRow(new Object[] {
                user.getId(),
                user.getUsername(),
                user.getKaryawan().getNama(),
                user.getPassword()
        });
    }

    @Override
    protected void onUpdateRow(User user) {
        int row = findIndexOf(user);
        setValueAt(user.getId(), row, 0);
        setValueAt(user.getUsername(), row, 1);
        setValueAt(user.getKaryawan().getNama(), row, 2);
        setValueAt(user.getPassword(), row, 3);
    }
}
