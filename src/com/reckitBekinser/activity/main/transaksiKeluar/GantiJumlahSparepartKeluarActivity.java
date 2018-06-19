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
package com.reckitBekinser.activity.main.transaksiKeluar;

import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.Label;
import com.dika.view.component.custom.DecimalFormattedTextField;
import com.reckitBekinser.model.DetailTransaksiSparepartKeluar;
import com.reckitBekinser.model.Sparepart;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public class GantiJumlahSparepartKeluarActivity extends InputActivity<GantiJumlahSparepartKeluarView>
        implements GantiJumlahSparepartKeluarView {

    private final GantiJumlahSparepartKeluarView view = new GantiJumlahSparepartKeluarViewImpl();
    private DetailTransaksiSparepartKeluar detailTransaksiSparepartKeluar;
    private OnChangedDetailJumlahKeluar onChangedDetailJumlahKeluar;

    public interface OnChangedDetailJumlahKeluar {
        void onChanged(int newJumlah);
    }

    public void setDetailTransaksiSparepartKeluar(DetailTransaksiSparepartKeluar detailTransaksiSparepartKeluar) {
        this.detailTransaksiSparepartKeluar = detailTransaksiSparepartKeluar;
        SwingUtilities.invokeLater(() -> {
            Sparepart sparepart = detailTransaksiSparepartKeluar.getSparepart();
            getJmlMaxLabel().setText("Jumlah Maksimum : "+sparepart.getStock());
            getJumlahField().setValue(detailTransaksiSparepartKeluar.getJumlah());
        });
    }

    public void setOnChangedDetailJumlahKeluar(OnChangedDetailJumlahKeluar onChangedDetailJumlahKeluar) {
        this.onChangedDetailJumlahKeluar = onChangedDetailJumlahKeluar;
    }

    private void saveNewJumlah() {
        if (!validateInput()) return;

        int jumlahDiminta = Integer.parseInt(getJumlahField().getValue().toString());
        detailTransaksiSparepartKeluar.setJumlah(jumlahDiminta);
        if (onChangedDetailJumlahKeluar != null) {
            onChangedDetailJumlahKeluar.onChanged(jumlahDiminta);
        }
        stop();
    }

    private boolean validateJumlah() {
        String value = getJumlahField().getValue().toString();

        if (value.isEmpty()) {
            showNotifOn(getJumlahField(), "Jumlah masih kosong");
            return false;
        }

        try {
            int jumlahDiminta = Integer.parseInt(value);

            Sparepart sparepart = detailTransaksiSparepartKeluar.getSparepart();
            if (jumlahDiminta > sparepart.getStock()) {
                showNotifOn(getJumlahField(), "Jumlah yang diminta melebihi jumlah stock");
                return false;
            }

            if (jumlahDiminta <= 0) {
                showNotifOn(getJumlahField(), "Jumlah yang diminta harus lebih dari 0");
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            showNotifOn(getJumlahField(), "Jumlah tidak valid");
            return false;
        }
    }

    @Override
    protected void initListener(GantiJumlahSparepartKeluarView v) {
        getCancelButton().addActionListener(e -> stop());
        getSaveButton().addActionListener(e -> saveNewJumlah());
    }

    @Override
    protected boolean validateInput() {
        return super.validateInput() && validateJumlah();
    }

    @NotNull
    @Override
    public GantiJumlahSparepartKeluarView getView() {
        return view;
    }

    @Override
    public Label getJmlMaxLabel() {
        return view.getJmlMaxLabel();
    }

    @Override
    public DecimalFormattedTextField getJumlahField() {
        return view.getJumlahField();
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
    public Dialog getRoot() {
        return view.getRoot();
    }

}
