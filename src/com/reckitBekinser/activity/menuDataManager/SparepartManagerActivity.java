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
import com.reckitBekinser.model.Sparepart;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public class SparepartManagerActivity extends CommonManagerActivity<Integer, Sparepart> {

    public SparepartManagerActivity() {
        super("Data Manager Sparepart");
    }

    @Override
    protected void onUpdateModel() {
        Sparepart sparepart = getModelOnSelectedRow();
        if (sparepart != null) {
            
        }
    }

    @Override
    protected void onDeleteModel() {
        
    }

    @Override
    protected void onAddModel() {

    }

    @NotNull
    @Override
    protected EntityTableModel<Sparepart> createTableModel(Table table) {
        return new SparepartTableModel(table);
    }

    @NotNull
    @Override
    protected DataReport onCreateDataReport() {
        return null;
    }

    @Override
    public int countData() {
        return 0;
    }

    @Override
    public void insertData(int firstResult, int maxResults) {

    }
}
