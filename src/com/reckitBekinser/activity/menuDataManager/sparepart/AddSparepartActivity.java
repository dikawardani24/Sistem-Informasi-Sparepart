package com.reckitBekinser.activity.menuDataManager.sparepart;

import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import com.reckitBekinser.activity.menuDataManager.KaryawanManagerActivity;
import com.reckitBekinser.model.Sparepart;
import com.reckitBekinser.service.SparepartServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AddSparepartActivity extends InputActivity<AddSparepartView> implements AddSparepartView {
    private final AddSparepartView view = new AddSparepartViewImpl();

    private void save() {
        if (!validateInput()) return;

        Sparepart sparepart = new Sparepart();
        sparepart.setNama(getNamaSparepartField().getText());

        String value = getJumlahSparepartField().getValue().toString();
        int jumlah = Integer.parseInt(value);
        sparepart.setJumlah(jumlah);

        sparepart.setKategori(getKategoriSparepartField().getText());
        sparepart.setKeterangan(getKeteranganSparepartField().getText());
        sparepart.setLevelRak(getLevelRakSparepartField().getText());
        sparepart.setNoRak(getNoRakSparepartField().getText());

        execute(new SparepartServiceImpl(), "Data Sparepart Berhasil Disimpan",
                "Data Sparepart Gagal Disimpan",
                service -> {
                    service.create(sparepart);
                    Activity<?> parent = getParent();
                    if (parent instanceof KaryawanManagerActivity) {
                        ((KaryawanManagerActivity) parent).refresh();
                    }
                    return service;
                });
    }

    @NotNull
    @Override
    public AddSparepartView getView() {
        return view;
    }

    @Override
    protected void initListener(AddSparepartView addSparePartView) {
        getSaveButton().addActionListener(e -> save());

        getClearButton().addActionListener(e -> clear());

        getCancelButton().addActionListener(e -> stop());
    }

    @Override
    public TextField getNamaSparepartField() {
        return view.getNamaSparepartField();
    }

    @Override
    public TextField getKategoriSparepartField() {
        return view.getKategoriSparepartField();
    }

    @Override
    public JFormattedTextField getJumlahSparepartField() {
        return view.getJumlahSparepartField();
    }

    @Override
    public TextField getNoRakSparepartField() {
        return view.getNoRakSparepartField();
    }

    @Override
    public TextField getLevelRakSparepartField() {
        return view.getLevelRakSparepartField();
    }

    @Override
    public TextArea getKeteranganSparepartField() {
        return view.getKeteranganSparepartField();
    }

    @Override
    public Button getSaveButton() {
        return view.getSaveButton();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Button getClearButton() {
        return view.getClearButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
