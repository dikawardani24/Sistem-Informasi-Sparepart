package com.reckitBekinser.activity.menuDataManager.supplier;

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

public class AddSupplierActivity extends InputActivity<AddSupplierView>
    implements AddSupplierView {
    private final AddSupplierView view = new AddSupplierViewImpl();

    private void save() {
        if (!validateInput()) return;

        Supplier supplier = new Supplier();
        initData(supplier);

        execute(new SupplierServiceImpl(), "Data Supplier Berhasil Disimpan",
                "Data Supplier Gagal Disimpan",
                service -> {
                    service.create(supplier);
                    Activity<?> parent = getParent();
                    if (parent instanceof SupplierManagerActivity) {
                        ((SupplierManagerActivity) parent).refresh();
                    }
                    return service;
                });
    }

    private void initData(Supplier supplier) {
        supplier.setNama(getNamaSupplierField().getText());
        supplier.setEmail(getEmailSupplierField().getText());
        supplier.setKeterangan(getKeteranganSupplierField().getText());
        supplier.setNoTelp1(getNoTelp1SupplierField().getText());
        supplier.setNoTelp2(getNoTelp2SupplierField().getText());
    }

    @NotNull
    @Override
    public AddSupplierView getView() {
        return view;
    }

    @Override
    protected void initListener(AddSupplierView addSupplierView) {
        getCancelButton().addActionListener(e -> stop());
        getClearButton().addActionListener(e -> clear());
        getSaveButton().addActionListener(e -> save());
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
