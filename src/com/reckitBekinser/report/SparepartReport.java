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
package com.reckitBekinser.report;

import com.dika.report.DataReport;
import com.dika.util.CollectionHelper;
import com.reckitBekinser.model.Sparepart;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

/**
 * @author dika
 */
public final class SparepartReport extends DataReport {

    private List<Sparepart> spareparts = Collections.emptyList();

    public SparepartReport() {
        super("Laporan Data Sparepart");
    }

    public void setSpareparts(List<Sparepart> spareparts) {
        this.spareparts = spareparts;
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "nama", "kategori", "jumlah",
                "noRak", "levelRak", "keterangan");

        spareparts.forEach(sparepart -> dataSource.add(
                sparepart.getId(),
                sparepart.getNama(),
                sparepart.getKategori(),
                sparepart.getJumlah(),
                sparepart.getNoRak(),
                sparepart.getLevelRak(),
                sparepart.getKeterangan()
        ));

        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID", "id", type.integerType());
        TextColumnBuilder<?> namaCol = createColumn("Nama", "nama", type.stringType());
        TextColumnBuilder<?> kategoriCol = createColumn("Kategori", "kategori", type.stringType());
        TextColumnBuilder<?> jumlahCol = createColumn("Jumlah", "jumlah", type.integerType());
        TextColumnBuilder<?> noRakCol = createColumn("No. Rak", "noRak", type.stringType());
        TextColumnBuilder<?> levelRakCol = createColumn("Level Rak", "levelRak", type.stringType());
        TextColumnBuilder<?> keteranganCol = createColumn("Keterangan", "keterangan", type.stringType());


        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, namaCol, kategoriCol, jumlahCol, noRakCol, levelRakCol, keteranganCol);

    }

}
