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
package com.reckitBekinser.activity.main.transaksiMasuk;

import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import com.reckitBekinser.model.Supplier;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public class DetailSupplierActivity extends InputActivity<DetailSupplierView>
    implements DetailSupplierView{
    private final DetailSupplierView view = new DetailSupplierViewImpl();

    @Override
    protected void initListener(DetailSupplierView detailSupplierView) {
        getCancelButton().addActionListener(e -> stop());
    }

    public void setSupplier(Supplier supplier) {
        SwingUtilities.invokeLater(() -> {
            getNamaSupplierField().setText(supplier.getNama());
            getNoTelp1SupplierField().setText(supplier.getNoTelp1());
            getNoTelp2SupplierField().setText(supplier.getNoTelp2());
            getEmailSupplierField().setText(supplier.getEmail());
            getKeteranganSupplierField().setText(supplier.getKeterangan());
        });
    }

    @NotNull
    @Override
    public DetailSupplierView getView() {
        return view;
    }

    @Override
    public TextField getNamaSupplierField() {
        return view.getNamaSupplierField();
    }

    @Override
    public TextField getNoTelp1SupplierField() {
        return view.getNoTelp1SupplierField();
    }

    @Override
    public TextField getNoTelp2SupplierField() {
        return view.getNoTelp2SupplierField();
    }

    @Override
    public TextField getEmailSupplierField() {
        return view.getEmailSupplierField();
    }

    @Override
    public TextArea getKeteranganSupplierField() {
        return view.getKeteranganSupplierField();
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
