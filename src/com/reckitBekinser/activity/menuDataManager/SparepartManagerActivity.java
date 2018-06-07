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
package com.reckitBekinser.activity.menuDataManager;

import com.dika.activity.CommonManagerActivity;
import com.dika.report.DataReport;
import com.dika.view.component.Table;
import com.dika.view.model.EntityTableModel;
import com.reckitBekinser.activity.menuDataManager.sparepart.AddSparepartActivity;
import com.reckitBekinser.activity.menuDataManager.sparepart.DeleteSparepartActivity;
import com.reckitBekinser.activity.menuDataManager.sparepart.UpdateSparepartActivity;
import com.reckitBekinser.tableModel.SparepartTableModel;
import com.reckitBekinser.model.Sparepart;
import com.reckitBekinser.report.SparepartReport;
import com.reckitBekinser.service.SparepartServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author dika
 */
public final class SparepartManagerActivity extends CommonManagerActivity<Integer, Sparepart> {

    public SparepartManagerActivity() {
        super("Data Manager Sparepart");
    }

    @Override
    protected void onUpdateModel() {
        Sparepart sparepart = getModelOnSelectedRow();
        if (sparepart != null) {
            UpdateSparepartActivity activity = startOther(UpdateSparepartActivity.class);
            activity.setSparepart(sparepart);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @Override
    protected void onDeleteModel() {
        Sparepart sparepart = getModelOnSelectedRow();
        if (sparepart != null) {
            DeleteSparepartActivity activity = startOther(DeleteSparepartActivity.class);
            activity.setSparepart(sparepart);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @Override
    protected void onAddModel() {
        startOther(AddSparepartActivity.class);
    }

    @NotNull
    @Override
    protected EntityTableModel<Sparepart> createTableModel(Table table) {
        return new SparepartTableModel(table);
    }

    @NotNull
    @Override
    protected DataReport onCreateDataReport() {
        SparepartReport report = new SparepartReport();
        report.setSpareparts(tableModel.getEntities());
        return report;
    }

    @Override
    public int countData() {
        return execute(new SparepartServiceImpl(), SparepartServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new SparepartServiceImpl(), sparepartService -> {
            List<Sparepart> spareparts = sparepartService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(spareparts);
            return sparepartService;
        });
    }
}
