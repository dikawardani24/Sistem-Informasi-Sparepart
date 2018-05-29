/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reckitBekinser.activity.menuDataManager.karyawan;

import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.*;
import com.reckitBekinser.activity.menuDataManager.KaryawanManagerActivity;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.service.KaryawanServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public final class UpdateKaryawanActivity extends InputActivity<UpdateKaryawanView> implements UpdateKaryawanView {
    private final UpdateKaryawanView view = new UpdateKaryawanViewImpl();
    private Karyawan karyawan;

    public void setKaryawan(Karyawan karyawan) {
        SwingUtilities.invokeLater(() -> {
            this.karyawan = karyawan;
            getIdKaryawanField().setText(String.valueOf(karyawan.getId()));
            getNamaField().setText(karyawan.getNama());
            getJenkelComboBox().setSelectedItem(karyawan.getJenisKelamin());
            getJabatanComboBox().setSelectedItem(karyawan.getJabatan());
            getNoTelpField().setText(karyawan.getNoHp());
            getNoKtpField().setText(karyawan.getNoKtp());
            getAlamatField().setText(karyawan.getAlamat());
        });
    }
    
    private void updateKaryawan() {
        if (!validateInput()) {
            return;
        }

        try {
            Karyawan old = (Karyawan) karyawan.clone();

            karyawan.setNama(getNamaField().getText());
            String jenkel = String.valueOf(getJenkelComboBox().getSelectedItem());
            karyawan.setJenisKelamin(jenkel);
            karyawan.setNoHp(getNoTelpField().getText());
            karyawan.setNoKtp(getNoKtpField().getText());
            karyawan.setAlamat(getAlamatField().getText());
            karyawan.setJabatan(String.valueOf(getJabatanComboBox().getSelectedItem()));

            if (old.equals(karyawan)) {
                showInfo("Tidak ada perubahan data dilakukan");
                return;
            }
            
            execute(new KaryawanServiceImpl(), "Data Karyawan Berhasil Disimpan", "Data Karyawan Gagal Disimpan",
                    karyawanService -> {
                        karyawanService.update(karyawan);
                        Activity<?> parent = getParent();
                        if (parent instanceof KaryawanManagerActivity) {
                            ((KaryawanManagerActivity) parent).refresh();
                        }
                        return karyawanService;
                    });
        } catch (CloneNotSupportedException e) {
            Logger.INSTANCE.printError(e);
        }

    }

    @Override
    protected boolean validateInput() {
        if (karyawan == null) {
            showFailed("Tidak ada data karyawan yang akan diupdate");
            return false;
        }
        return super.validateInput();
    }

    @NotNull
    @Override
    public UpdateKaryawanView getView() {
        return view;
    }

    @Override
    protected void initListener(UpdateKaryawanView v) {
        getSaveButton().addActionListener(evt -> updateKaryawan());

        getClearButton().addActionListener(evt -> clear());

        getCancelButton().addActionListener(evt -> stop());
    }

    @Override
    public TextField getIdKaryawanField() {
        return view.getIdKaryawanField();
    }

    @Override
    public TextField getNamaField() {
        return view.getNamaField();
    }

    @Override
    public ComboBox<String> getJenkelComboBox() {
        return view.getJenkelComboBox();
    }

    @Override
    public ComboBox<String> getJabatanComboBox() {
        return view.getJabatanComboBox();
    }

    @Override
    public TextField getNoTelpField() {
        return view.getNoTelpField();
    }

    @Override
    public TextField getNoKtpField() {
        return view.getNoKtpField();
    }

    @Override
    public TextArea getAlamatField() {
        return view.getAlamatField();
    }

    @Override
    public Button getClearButton() {
        return view.getClearButton();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Button getSaveButton() {
        return view.getSaveButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
