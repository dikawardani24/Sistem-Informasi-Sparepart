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

import com.dika.activity.Activity;
import com.dika.activity.ManipulateEntityActivity;
import com.dika.view.component.*;
import com.dika.view.component.custom.HeaderLabel;
import com.reckitBekinser.activity.menuDataManager.KaryawanManagerActivity;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.service.KaryawanServiceImpl;
import org.jetbrains.annotations.NotNull;

/**
 * @author dika
 */
public final class ManipulateKaryawanActivity extends ManipulateEntityActivity<ManipulateKaryawanView, Karyawan> implements ManipulateKaryawanView {
    private final ManipulateKaryawanView view = new ManipulateKaryawanViewImpl();

    @NotNull
    @Override
    public Karyawan createNewEntity() {
        return new Karyawan();
    }

    @Override
    public void viewOldDataEntity(Karyawan entity) {
        getNamaField().setText(entity.getNama());
        getJenkelComboBox().setSelectedItem(entity.getJenisKelamin());
        getJabatanComboBox().setSelectedItem(entity.getJabatan());
        getNoTelpField().setText(entity.getNoHp());
        getNoKtpField().setText(entity.getNoKtp());
        getAlamatField().setText(entity.getAlamat());
    }

    @Override
    protected void initData(Karyawan karyawan) {
        karyawan.setNama(getNamaField().getText());
        String jenkel = String.valueOf(getJenkelComboBox().getSelectedItem());
        karyawan.setJenisKelamin(jenkel);
        karyawan.setNoHp(getNoTelpField().getText());
        karyawan.setNoKtp(getNoKtpField().getText());
        karyawan.setAlamat(getAlamatField().getText());
        karyawan.setJabatan(String.valueOf(getJabatanComboBox().getSelectedItem()));
    }

    @Override
    protected void saveNewEntity(Karyawan karyawan) {
        execute(new KaryawanServiceImpl(), "Data Karyawan Berhasil Disimpan", "Data Karyawan Gagal Disimpan",
                karyawanService -> {
                    karyawanService.create(karyawan);
                    Activity<?> parent = getParent();
                    if (parent instanceof KaryawanManagerActivity) {
                        ((KaryawanManagerActivity) parent).refresh();
                    }

                    clear();
                    return karyawanService;
                });
    }

    @Override
    protected void updateEntity(Karyawan karyawan) {
        execute(new KaryawanServiceImpl(), "Data Karyawan Berhasil Disimpan", "Data Karyawan Gagal Disimpan",
                karyawanService -> {
                    karyawanService.update(karyawan);
                    Activity<?> parent = getParent();
                    if (parent instanceof KaryawanManagerActivity) {
                        ((KaryawanManagerActivity) parent).refresh();
                    }
                    return karyawanService;
                });
    }

    @NotNull
    @Override
    public ManipulateKaryawanView getView() {
        return view;
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

    @NotNull
    @Override
    public HeaderLabel getHeaderLabel() {
        return view.getHeaderLabel();
    }
}
