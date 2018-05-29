package com.reckitBekinser.activity.menuDataManager;

import com.alee.laf.table.WebTable;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.model.Sparepart;

public final class SparepartTableModel extends EntityTableModel<Sparepart> {
    public SparepartTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Sparepart", "Nama", "Kategori",
                "Stock", "No. Rak", "Level Rak", "Keterangan");
    }

    @Override
    protected void onInsertNewRow(Sparepart sparepart) {
        addRow(new Object[] {
                sparepart.getId(),
                sparepart.getNama(),
                sparepart.getKategori(),
                sparepart.getJumlah(),
                sparepart.getNoRak(),
                sparepart.getLevelRak(),
                sparepart.getKeterangan()
        });
    }

    @Override
    protected void onUpdateRow(Sparepart sparepart) {
        int row = findIndexOf(sparepart);

        setValueAt(sparepart.getId(), row, 0);
        setValueAt(sparepart.getNama(), row, 1);
        setValueAt(sparepart.getKategori(), row, 2);
        setValueAt(sparepart.getJumlah(), row, 3);
        setValueAt(sparepart.getNoRak(), row, 4);
        setValueAt(sparepart.getLevelRak(), row, 5);
        setValueAt(sparepart.getKeterangan(), row, 6);
    }
}
