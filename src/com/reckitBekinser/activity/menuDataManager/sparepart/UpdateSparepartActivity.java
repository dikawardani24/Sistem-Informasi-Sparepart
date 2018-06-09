package com.reckitBekinser.activity.menuDataManager.sparepart;

import com.dika.Logger;
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

public class UpdateSparepartActivity extends InputActivity<UpdateSparepartView>
    implements UpdateSparepartView {
    private final UpdateSparepartView view = new UpdateSparepartViewImpl();
    private Sparepart sparepart;

    public void setSparepart(Sparepart sparepart) {
        SwingUtilities.invokeLater(() -> {
            this.sparepart = sparepart;
            getNamaSparepartField().setText(sparepart.getNama());
            getKategoriSparepartField().setText(sparepart.getKategori());
            getJumlahSparepartField().setValue(sparepart.getStock());
            getNoRakSparepartField().setText(sparepart.getNoRak());
            getLevelRakSparepartField().setText(sparepart.getLevelRak());
            getKeteranganSparepartField().setText(sparepart.getKeterangan());
        });
    }

    private void update() {
        if (!validateInput()) {
            return;
        }

        execute(new SparepartServiceImpl(), "Data Sparepart Berhasil Diperbaharui",
                "Data Sparepart Gagal Diperbaharui",
                service -> {
                    service.update(sparepart);
                    Activity<?> parent = getParent();
                    if (parent instanceof KaryawanManagerActivity) {
                        ((KaryawanManagerActivity) parent).refresh();
                    }
                    return service;
                });
    }

    private void initData(Sparepart sparepart) {
        sparepart.setNama(getNamaSparepartField().getText());

        String value = getJumlahSparepartField().getValue().toString();
        int jumlah = Integer.parseInt(value);
        sparepart.setStock(jumlah);

        sparepart.setKategori(getKategoriSparepartField().getText());
        sparepart.setKeterangan(getKeteranganSparepartField().getText());
        sparepart.setLevelRak(getLevelRakSparepartField().getText());
        sparepart.setNoRak(getNoRakSparepartField().getText());
    }

    @Override
    protected boolean validateInput() {
        if (!super.validateInput()) return false;

        if (sparepart == null) {
            showFailed("Tidak Ada Data Sparepart");
            return false;
        }

        try {
            Sparepart old = (Sparepart) sparepart.clone();
            initData(sparepart);

            if (old.equals(sparepart)) {
                showInfo("Tidak Ada Perubahan Dilakukan");
                return false;
            }

            return true;
        } catch (CloneNotSupportedException e) {
            Logger.INSTANCE.printError(e);
            showFailed("Tidak Dapat Memvalidasi Data Sparepart");
            return false;
        }
    }

    @NotNull
    @Override
    public UpdateSparepartView getView() {
        return view;
    }

    @Override
    protected void initListener(UpdateSparepartView updateSparepartView) {
        getCancelButton().addActionListener(e -> stop());
        getClearButton().addActionListener(e -> clear());
        getUpdateButton().addActionListener(e -> update());
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
    public Button getUpdateButton() {
        return view.getUpdateButton();
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
