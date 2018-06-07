package com.reckitBekinser.tableModel;

import com.alee.laf.table.WebTable;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.model.DetailPermintaanSparepart;
import com.reckitBekinser.model.Sparepart;

public final class DetailPermintaanSparepartTableModel extends EntityTableModel<DetailPermintaanSparepart> {
    public DetailPermintaanSparepartTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Sparepart", "Nama", "Stock", "Jumlah Diminta");
    }

    @Override
    protected void onInsertNewRow(DetailPermintaanSparepart detailPermintaanSparepart) {
        Sparepart sparepart = detailPermintaanSparepart.getSparepart();
        addRow(new Object[] {
                sparepart.getId(),
                sparepart.getNama(),
                sparepart.getJumlah(),
                detailPermintaanSparepart.getJumlah()
        });
    }

    @Override
    protected void onUpdateRow(DetailPermintaanSparepart detailPermintaanSparepart) {
        int row = findIndexOf(detailPermintaanSparepart);

        Sparepart sparepart = detailPermintaanSparepart.getSparepart();
        setValueAt(sparepart.getId(), row, 0);
        setValueAt(sparepart.getNama(), row, 1);
        setValueAt(sparepart.getJumlah(), row, 2);
        setValueAt(detailPermintaanSparepart.getJumlah(), row, 3);
    }
}
