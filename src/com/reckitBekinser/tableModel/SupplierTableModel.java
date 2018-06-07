package com.reckitBekinser.tableModel;

import com.alee.laf.table.WebTable;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.model.Supplier;

public final class SupplierTableModel extends EntityTableModel<Supplier> {
    public SupplierTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Supplier", "Nama", "No. Telp1", "No. Telp2", "Email", "Keterangan");
    }

    @Override
    protected void onInsertNewRow(Supplier supplier) {
        addRow(new Object[] {
                supplier.getId(),
                supplier.getNama(),
                supplier.getNoTelp1(),
                supplier.getNoTelp2(),
                supplier.getEmail(),
                supplier.getKeterangan()
        });
    }

    @Override
    protected void onUpdateRow(Supplier supplier) {
        int row = findIndexOf(supplier);

        setValueAt(supplier.getId(), row, 0);
        setValueAt(supplier.getNama(), row, 1);
        setValueAt(supplier.getNoTelp1(), row, 2);
        setValueAt(supplier.getNoTelp2(), row, 3);
        setValueAt(supplier.getEmail(), row, 4);
        setValueAt(supplier.getKeterangan(), row, 5);
    }
}
