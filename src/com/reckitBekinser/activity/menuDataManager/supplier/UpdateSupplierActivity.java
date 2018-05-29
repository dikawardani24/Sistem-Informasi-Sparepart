package com.reckitBekinser.activity.menuDataManager.supplier;

import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import com.reckitBekinser.activity.menuDataManager.SupplierManagerActivity;
import com.reckitBekinser.model.Supplier;
import com.reckitBekinser.service.SupplierServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class UpdateSupplierActivity extends InputActivity<UpdateSupplierView>
    implements UpdateSupplierView {
    private final UpdateSupplierView view = new UpdateSupplierViewImpl();
    private Supplier supplier;

    public void update() {
        if (!validateInput()) {
            return;
        }

        execute(new SupplierServiceImpl(), "Data Supplier Berhasil Diperbaharui",
                "Data Supplier Gagal Diperbaharui",
                service -> {
                    service.update(supplier);
                    Activity<?> parent = getParent();
                    if (parent instanceof SupplierManagerActivity) {
                        ((SupplierManagerActivity) parent).refresh();
                    }
                    return service;
                });
    }

    public void setSupplier(Supplier supplier) {
        SwingUtilities.invokeLater(() -> {
            this.supplier = supplier;
            getNamaSupplierField().setText(supplier.getNama());
            getNoTelp1SupplierField().setText(supplier.getNoTelp1());
            getNoTelp2SupplierField().setText(supplier.getNoTelp2());
            getEmailSupplierField().setText(supplier.getEmail());
            getKeteranganSupplierField().setText(supplier.getKeterangan());
        });
    }

    private void initData(Supplier supplier) {
        supplier.setNama(getNamaSupplierField().getText());
        supplier.setEmail(getEmailSupplierField().getText());
        supplier.setKeterangan(getKeteranganSupplierField().getText());
        supplier.setNoTelp1(getNoTelp1SupplierField().getText());
        supplier.setNoTelp2(getNoTelp2SupplierField().getText());
    }

    @Override
    protected boolean validateInput() {
        if (!super.validateInput()) return false;

        if (supplier == null) {
            showFailed("Tidak Ada Data Supplier");
            return false;
        }

        try {
            Supplier old = (Supplier) supplier.clone();
            initData(supplier);

            if (old.equals(supplier)) {
                showInfo("Tidak Ada Perubahan Dilakukan");
                return false;
            }

            return true;
        } catch (CloneNotSupportedException e) {
            Logger.INSTANCE.printError(e);
            showFailed("Tidak Dapat Memvalidasi Data Supplier");
            return false;
        }
    }
    @NotNull
    @Override
    public UpdateSupplierView getView() {
        return view;
    }

    @Override
    protected void initListener(UpdateSupplierView addSupplierView) {
        getCancelButton().addActionListener(e -> stop());
        getClearButton().addActionListener(e -> clear());
        getSaveButton().addActionListener(e -> update());
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
