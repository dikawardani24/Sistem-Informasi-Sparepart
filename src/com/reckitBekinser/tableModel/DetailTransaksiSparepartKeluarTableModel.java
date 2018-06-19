package com.reckitBekinser.tableModel;

import com.alee.laf.table.WebTable;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.model.DetailTransaksiSparepartKeluar;
import com.reckitBekinser.model.Sparepart;

public final class DetailTransaksiSparepartKeluarTableModel extends EntityTableModel<DetailTransaksiSparepartKeluar> {
    public DetailTransaksiSparepartKeluarTableModel(WebTable tableHolder) {
        super(tableHolder);
        initColumns();
    }

    @Override
    protected void initColumns() {
        addColumns("ID Sparepart", "Nama", "Stock", "Jumlah Keluar");
    }

    @Override
    protected void onInsertNewRow(DetailTransaksiSparepartKeluar detailTransaksiSparepartKeluar) {
        Sparepart sparepart = detailTransaksiSparepartKeluar.getSparepart();
        addRow(new Object[] {
                sparepart.getNama(),
                sparepart.getStock(),
                detailTransaksiSparepartKeluar.getJumlah()
        });
    }

    @Override
    protected void onUpdateRow(DetailTransaksiSparepartKeluar detailTransaksiSparepartKeluar) {
        int row = findIndexOf(detailTransaksiSparepartKeluar);

        Sparepart sparepart = detailTransaksiSparepartKeluar.getSparepart();
        setValueAt(sparepart.getId(), row, 0);
        setValueAt(sparepart.getNama(), row, 1);
        setValueAt(sparepart.getStock(), row, 2);
        setValueAt(detailTransaksiSparepartKeluar.getJumlah(), row, 3);
    }
}
