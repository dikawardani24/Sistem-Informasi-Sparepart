package com.reckitBekinser.activity.menuDataManager;

import com.dika.activity.CommonManagerActivity;
import com.dika.report.DataReport;
import com.dika.view.component.Table;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.activity.menuDataManager.supplier.AddSupplierActivity;
import com.reckitBekinser.activity.menuDataManager.supplier.DeleteSupplierActivity;
import com.reckitBekinser.activity.menuDataManager.supplier.UpdateSupplierActivity;
import com.reckitBekinser.tableModel.SupplierTableModel;
import com.reckitBekinser.model.Supplier;
import com.reckitBekinser.report.SupplierReport;
import com.reckitBekinser.service.SupplierServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SupplierManagerActivity extends CommonManagerActivity<Integer, Supplier> {
    public SupplierManagerActivity() {
        super("Data Manager Supplier");
    }

    @Override
    protected void onUpdate(Supplier supplier) {
        UpdateSupplierActivity activity = startOther(UpdateSupplierActivity.class);
        activity.setSupplier(supplier);
    }

    @Override
    protected void onDelete(Supplier supplier) {
        DeleteSupplierActivity activity = startOther(DeleteSupplierActivity.class);
        activity.setSupplier(supplier);
    }

    @Override
    protected void onAddEntity() {
        startOther(AddSupplierActivity.class);
    }

    @NotNull
    @Override
    protected EntityTableModel<Supplier> createTableModel(Table table) {
        return new SupplierTableModel(table);
    }

    @NotNull
    @Override
    protected DataReport onCreateDataReport() {
        SupplierReport report = new SupplierReport();
        report.setSuppliers(tableModel.getEntities());
        return report;
    }

    @Override
    public int countData() {
        return execute(new SupplierServiceImpl(), SupplierServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new SupplierServiceImpl(), supplierService -> {
            List<Supplier> suppliers = supplierService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(suppliers);
            return supplierService;
        });
    }
}
