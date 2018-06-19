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
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;
import com.reckitBekinser.Session;
import com.reckitBekinser.activity.MainActivity;
import com.reckitBekinser.activity.main.transaksiMasuk.DetailSupplierActivity;
import com.reckitBekinser.model.DetailTransaksiSparepartMasuk;
import com.reckitBekinser.model.Supplier;
import com.reckitBekinser.model.TransaksiSparepartMasuk;
import com.reckitBekinser.service.DetailTransaksiSparepartMasukServiceImpl;
import com.reckitBekinser.service.TransaksiSparepartMasukServiceImpl;
import kotlin.Unit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dika
 */
public class SparepartMasukController extends MainController implements SparepartMasukContainer {

    private final SparepartMasukContainer container = new SparepartMasukContainerImpl();
    private Supplier supplier;
    private List<DetailTransaksiSparepartMasuk> detailTransaksiSparepartMasuks =
            new ArrayList<>();

    public SparepartMasukController(MainActivity mainActivity) {
        super(mainActivity, "Sparepart Masuk Controller");
    }

    private void pilihSupplier() {

    }

    private void addSparepartMasuk() {

    }

    private void viewDetailSupplier() {
        if (!validateSupplier()) {
            return;
        }

        DetailSupplierActivity activity = startOther(DetailSupplierActivity.class);
        activity.setSupplier(supplier);
    }

    private void simpan() {
        if (!validateAllInput()) {
            return;
        }

        TransaksiSparepartMasuk transaksiSparepartMasuk = new TransaksiSparepartMasuk();
        transaksiSparepartMasuk.setNoPurchaseOrder(getNoPurchaseOrderField().getText());
        transaksiSparepartMasuk.setPenerima(Session.getInstance().getKaryawan());
        transaksiSparepartMasuk.setSupplier(supplier);
        transaksiSparepartMasuk.setTgl(CalendarHelper.INSTANCE.today());

        boolean isTransaksiSaved = execute(new TransaksiSparepartMasukServiceImpl(), service -> {
            try {
                service.create(transaksiSparepartMasuk);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        if (!isTransaksiSaved) {
            showFailed("Tidak dapat menyimpan data transaksi");
            return;
        }

        saveDetail(transaksiSparepartMasuk);
    }

    private void saveDetail(TransaksiSparepartMasuk transaksiSparepartMasuk) {
        for (DetailTransaksiSparepartMasuk detailTransaksiSparepartMasuk : detailTransaksiSparepartMasuks) {
            detailTransaksiSparepartMasuk.setTransaksiSparepartMasuk(transaksiSparepartMasuk);

            boolean detailSaved = execute(new DetailTransaksiSparepartMasukServiceImpl(), service -> {
                try {
                    service.create(detailTransaksiSparepartMasuk);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            });

            if (!detailSaved) {
                execute(new TransaksiSparepartMasukServiceImpl(), service -> {
                    try {
                        service.destroy(transaksiSparepartMasuk);
                    } catch (Exception e) {
                        showFailed("Tidak dapat menghapus data transaksi yang sudah tersimpan sebelumnya");
                    }

                    return Unit.INSTANCE;
                });

                return;
            }
        }

        showSucceed("Data transaksi berhasil disimpan");
        clear();
    }

    private boolean validateNoPurchaseOrder() {
        String noPurchaseOrder = getNoPurchaseOrderField().getText();
        if (noPurchaseOrder.isEmpty()) {
            showNotifOn(getNoPurchaseOrderField(), " No. PO masih kosong");
            return false;
        }

        return true;
    }

    private boolean validateSupplier() {
        String namaSupplier = getNamaSupplierField().getText();
        if (namaSupplier.isEmpty() || supplier == null) {
            showNotifOn(getChooseSupplierButton(), "Tekan tombol ini untuk memimilih supplier");
            return false;
        }

        return true;
    }

    private boolean validateAllInput() {
        return validateNoPurchaseOrder() && validateSupplier();
    }

    @Override
    public void init() {
        getChooseSupplierButton().addActionListener(e -> pilihSupplier());
        getSeeDetailSupplierButton().addActionListener(e -> viewDetailSupplier());
        getAddSparepartButton().addActionListener(e -> addSparepartMasuk());

        getChangeJumlahSparepartMenuItem().addActionListener(e -> gantiJumlahSparepart());
        getRemoveSparepartMenuItem().addActionListener(e -> hapusSparepart());

        getSaveButton().addActionListener(e -> simpan());
        getClearButton().addActionListener(e -> clear());
    }

    @Override
    public void clear() {
        getNamaSupplierField().clear();
        getNoPurchaseOrderField().clear();
        supplier = null;
        detailTransaksiSparepartMasuks.clear();
    }

    @Override
    public TextField getNoPurchaseOrderField() {
        return container.getNoPurchaseOrderField();
    }

    @Override
    public TextField getNamaSupplierField() {
        return container.getNamaSupplierField();
    }

    @Override
    public Button getChooseSupplierButton() {
        return container.getChooseSupplierButton();
    }

    @Override
    public Button getSeeDetailSupplierButton() {
        return container.getSeeDetailSupplierButton();
    }

    @Override
    public Button getAddSparepartButton() {
        return container.getAddSparepartButton();
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
    public WebMenuItem getRemoveSparepartMenuItem() {
        return container.getRemoveSparepartMenuItem();
    }

    @Override
    public WebMenuItem getChangeJumlahSparepartMenuItem() {
        return container.getChangeJumlahSparepartMenuItem();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return container.getPagingTableView();
    }

    @Override
    public Panel getRoot() {
        return container.getRoot();
    }
}
