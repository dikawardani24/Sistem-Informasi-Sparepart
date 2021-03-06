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
import com.reckitBekinser.activity.menuDataManager.karyawan.DeleteKaryawanActivity;
import com.reckitBekinser.activity.menuDataManager.karyawan.ManipulateKaryawanActivity;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.report.KaryawanReport;
import com.reckitBekinser.service.KaryawanServiceImpl;
import com.reckitBekinser.tableModel.KaryawanTableModel;
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
    protected void onAddEntity() {
        ManipulateKaryawanActivity activity = startOther(ManipulateKaryawanActivity.class);
        activity.setTitle("Tambah Data Karyawan");
    }

    @Override
    protected void onUpdate(Karyawan karyawan) {
        ManipulateKaryawanActivity activity = startOther(ManipulateKaryawanActivity.class);
        activity.setTitle("Update Data Karyawan");
        activity.setEntity(karyawan);
    }

    @Override
    protected void onDelete(Karyawan karyawan) {
        DeleteKaryawanActivity activity = startOther(DeleteKaryawanActivity.class);
        activity.setKaryawan(karyawan);
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
