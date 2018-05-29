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
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DeleteSupplierActivity extends InputActivity<DeleteSupplierView>
    implements DeleteSupplierView{
    private final DeleteSupplierView view = new DeleteSupplierViewImpl();

    private Supplier supplier;

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

    private void delete() {
        if (supplier == null) {
            showFailed("Tidak Ada Data Supplier Untuk Dihapus");
            return;
        }

        execute(new SupplierServiceImpl(), supplierService -> {
            try {
                supplierService.destroy(supplier);
                Activity<?> parent = getParent();
                if (parent instanceof SupplierManagerActivity) {
                    ((SupplierManagerActivity) parent).refresh();
                }
                showSucceed("Data Supplier Berhasil Dihapus");
                stop();
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data Supplier Gagal Dihapus");
            }
            return Unit.INSTANCE;
        });
    }

    @NotNull
    @Override
    public DeleteSupplierView getView() {
        return view;
    }

    @Override
    protected void initListener(DeleteSupplierView deleteSupplierView) {
        getCancelButton().addActionListener(e -> stop());
        getDeleteButton().addActionListener(e -> delete());
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
    public Button getDeleteButton() {
        return view.getDeleteButton();
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
