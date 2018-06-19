/*
 * Copyright (c) 2018 dika.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    dika - initial API and implementation and/or initial documentation
 */
package com.reckitBekinser.activity.main;

import com.alee.laf.menu.WebMenuItem;
import com.dika.util.CalendarHelper;
import com.dika.view.component.Button;
import com.dika.view.component.Panel;
import com.dika.view.component.Table;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;
import com.reckitBekinser.Session;
import com.reckitBekinser.activity.MainActivity;
import com.reckitBekinser.activity.main.transaksiKeluar.GantiJumlahSparepartKeluarActivity;
import com.reckitBekinser.model.*;
import com.reckitBekinser.service.DetailPermintaanSparepartServiceImpl;
import com.reckitBekinser.service.DetailTransaksiSparepartKeluarServiceImpl;
import com.reckitBekinser.service.PermintaanSparepartServiceImpl;
import com.reckitBekinser.service.TransaksiSparepartKeluarServiceImpl;
import com.reckitBekinser.tableModel.DetailTransaksiSparepartKeluarTableModel;
import kotlin.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author dika
 */
public class SparepartKeluarController extends MainController implements SparepartKeluarContainer {
    private final SparepartKeluarContainer container = new SparepartKeluarContainerImpl();
    private PermintaanSparepart permintaanSparepart;
    private DetailTransaksiSparepartKeluarTableModel tableModel;
    private final List<DetailTransaksiSparepartKeluar> sparepartKeluarList = new ArrayList<>();

    public SparepartKeluarController(MainActivity mainActivity) {
        super(mainActivity, "Sparepart Keluar");
    }

    private void findDataPermintaan() {
        if (!validateIdPermintaan()) return;

        int idPermintaan = Integer.parseInt(getIdPermintaanField().getText());
        permintaanSparepart = execute(new PermintaanSparepartServiceImpl(), permintaanSparepartService -> {
            try {
                return permintaanSparepartService.findBy(idPermintaan);
            } catch (Exception e) {
                return null;
            }
        });

        if (permintaanSparepart == null) {
            showFailed("Tidak menemukan data permintaan dengan ID : "+idPermintaan);
            return;
        }

        viewDetailPermintaan(permintaanSparepart);
    }

    private void viewDetailPermintaan(PermintaanSparepart permintaanSparepart) {
        Karyawan peminta = permintaanSparepart.getTeknisi();
        getIdPemintaField().setText(String.valueOf(peminta.getId()));
        getNamaPemintaField().setText(peminta.getNama());

        List<DetailPermintaanSparepart> permintaanSparepartList = execute(new DetailPermintaanSparepartServiceImpl(), service -> {
            try {
                return service.findsBy(permintaanSparepart);
            } catch (Exception e) {
                return Collections.emptyList();
            }
        });

        if (permintaanSparepartList.isEmpty()) {
            showInfo("Tidak menemukan detail permintaan sparepart");
            return;
        }

        for (DetailPermintaanSparepart detailPermintaanSparepart : permintaanSparepartList) {
            DetailTransaksiSparepartKeluar sparepartKeluar = new DetailTransaksiSparepartKeluar();

            sparepartKeluar.setSparepart(detailPermintaanSparepart.getSparepart());
            sparepartKeluar.setJumlah(detailPermintaanSparepart.getJumlah());
            sparepartKeluarList.add(sparepartKeluar);
        }
        tableModel.insert(sparepartKeluarList);
    }

    private boolean validateIdPermintaan() {
        String idPermintaanString = getIdPermintaanField().getText();

        if (idPermintaanString.isEmpty()) {
            showNotifOn(getIdPermintaanField(), "ID permintaan masih kosong");
            return false;
        }

        try {
            Integer.parseInt(idPermintaanString);
            return true;
        } catch (NumberFormatException e) {
            showNotifOn(getIdPermintaanField(), "ID permintaan tidak valid");
            return false;
        }
    }

    private boolean validatePermintaan() {
        if (permintaanSparepart == null) {
            showNotifOn(getSearchButton(), "Tekan tombol ini untuk mencari data permintaan sparepart");
            return false;
        }
        return true;
    }

    private boolean validateAllInput() {
        return validateIdPermintaan() && validatePermintaan();
    }

    private void simpan() {
        if (!validateAllInput()) return;

        TransaksiSparepartKeluar transaksiSparepartKeluar = new TransaksiSparepartKeluar();

        transaksiSparepartKeluar.setPemberiIzin(Session.getInstance().getKaryawan());
        transaksiSparepartKeluar.setTgl(CalendarHelper.INSTANCE.today());
        transaksiSparepartKeluar.setPermintaanSparepart(permintaanSparepart);

        boolean transaksiSaved = execute(new TransaksiSparepartKeluarServiceImpl(), service -> {
            try {
                service.create(transaksiSparepartKeluar);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        if (!transaksiSaved) {
            showFailed("Tidak dapat menyimpan data transaksi");
            return;
        }

        saveDetail(transaksiSparepartKeluar);
    }

    private void saveDetail(TransaksiSparepartKeluar transaksiSparepartKeluar) {
        for (DetailTransaksiSparepartKeluar detail : sparepartKeluarList) {

            detail.setTransaksiSparepartKeluar(transaksiSparepartKeluar);

            boolean detailSaved = execute(new DetailTransaksiSparepartKeluarServiceImpl(), service -> {
                try {
                    service.create(detail);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            });

            if (!detailSaved) {
                execute(new TransaksiSparepartKeluarServiceImpl(), service -> {
                    try {
                        service.destroy(transaksiSparepartKeluar);
                    } catch (Exception e) {
                        showFailed("Tidak dapat menghapus data transaksi yang sudah tersimpan sebelumnya");
                    }

                    return Unit.INSTANCE;
                });
                return;
            }
        }

        showSucceed("Data Transaksi berhasil disimpan");
        clear();
    }

    private DetailTransaksiSparepartKeluar getSelectedFromRow(int row) {
        if (row < 0) {
            return null;
        }

        return tableModel.getEntities().get(row);
    }

    private boolean isNoRowSelected(int row) {
        if (row < 0) {
            showInfo("Tidak ada baris terpilih");
            return true;
        }

        return false;
    }

    private int getSelectedRow() {
        return getPagingTableView().getTable().getSelectedRow();
    }

    private void hapusDetailPermintaan() {
        int row = getSelectedRow();
        if (isNoRowSelected(row)){
            return;
        }

        DetailTransaksiSparepartKeluar detailTransaksiSparepartKeluar =
                getSelectedFromRow(row);
        sparepartKeluarList.remove(detailTransaksiSparepartKeluar);
        tableModel.removeRow(row);
    }

    private void gantiJumlahPermintaan() {
        int row = getSelectedRow();
        if (isNoRowSelected(row)) {
            return;
        }

        DetailTransaksiSparepartKeluar detailTransaksiSparepartKeluar =
                getSelectedFromRow(row);
        GantiJumlahSparepartKeluarActivity activity = startOther(GantiJumlahSparepartKeluarActivity.class);
        activity.setDetailTransaksiSparepartKeluar(detailTransaksiSparepartKeluar);
        activity.setOnChangedDetailJumlahKeluar(newJumlah -> tableModel.update(detailTransaksiSparepartKeluar));
    }

    @Override
    public void clear() {
        container.clear();
        permintaanSparepart = null;
        sparepartKeluarList.clear();
        tableModel.clear();
    }

    @Override
    public void init() {
        Table table = getPagingTableView().getTable();
        tableModel = new DetailTransaksiSparepartKeluarTableModel(table);

        getSearchButton().addActionListener(e -> findDataPermintaan());
        getSaveButton().addActionListener(e -> simpan());
        getClearButton().addActionListener(e -> clear());

        getCancelMenuItem().addActionListener(e -> hapusDetailPermintaan());
        getChangeJumlahMenuItem().addActionListener(e -> gantiJumlahPermintaan());
    }

    @Override
    public TextField getIdPermintaanField() {
        return container.getIdPermintaanField();
    }

    @Override
    public TextField getIdPemintaField() {
        return container.getIdPemintaField();
    }

    @Override
    public TextField getNamaPemintaField() {
        return container.getNamaPemintaField();
    }

    @Override
    public WebMenuItem getChangeJumlahMenuItem() {
        return container.getChangeJumlahMenuItem();
    }

    @Override
    public WebMenuItem getCancelMenuItem() {
        return container.getCancelMenuItem();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return container.getPagingTableView();
    }

    @Override
    public Button getSearchButton() {
        return container.getSearchButton();
    }

    @Override
    public Button getSaveButton() {
        return container.getSaveButton();
    }

    @Override
    public Button getClearButton() {
        return container.getClearButton();
    }

    @Override
    public Panel getRoot() {
        return container.getRoot();
    }
}
