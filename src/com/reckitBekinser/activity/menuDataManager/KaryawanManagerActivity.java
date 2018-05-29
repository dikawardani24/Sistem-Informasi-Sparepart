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
import com.reckitBekinser.activity.menuDataManager.karyawan.AddKaryawanActivity;
import com.reckitBekinser.activity.menuDataManager.karyawan.DeleteKaryawanActivity;
import com.reckitBekinser.activity.menuDataManager.karyawan.UpdateKaryawanActivity;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.report.KaryawanReport;
import com.reckitBekinser.service.KaryawanServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 * @author dika
 */
public class KaryawanManagerActivity extends CommonManagerActivity<Integer, Karyawan> {

    public KaryawanManagerActivity() {
        super("Data Manager Karyawan");
    }

    @Override
    protected void onAddModel() {
        startOther(AddKaryawanActivity.class);
    }

    @Override
    protected void onUpdateModel() {
        Karyawan karyawan = getModelOnSelectedRow();
        if (karyawan != null) {
            UpdateKaryawanActivity activity = startOther(UpdateKaryawanActivity.class);
            activity.setKaryawan(karyawan);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @Override
    protected void onDeleteModel() {
        Karyawan karyawan = getModelOnSelectedRow();
        if (karyawan != null) {
            DeleteKaryawanActivity activity = startOther(DeleteKaryawanActivity.class);
            activity.setKaryawan(karyawan);
        } else {
            showInfo("Tidak ada baris terpilih");
        }
    }

    @NotNull
    @Override
    protected EntityTableModel<Karyawan> createTableModel(Table table) {
        return new KaryawanTableModel(table);
    }

    @NotNull
    @Override
    protected DataReport onCreateDataReport() {
        KaryawanReport report = new KaryawanReport();
        report.setKaryawans(tableModel.getEntities());
        return report;
    }

    @Override
    public int countData() {
        return execute(new KaryawanServiceImpl(), KaryawanServiceImpl::count);
    }

    @Override
    public void insertData(int firstResult, int maxResults) {
        execute(new KaryawanServiceImpl(), karyawanService -> {
            List<Karyawan> karyawans = karyawanService.findAll(maxResults, firstResult);
            tableModel.clear();
            tableModel.insert(karyawans);
            return karyawanService;
        });
    }

}
