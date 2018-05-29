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
import java.util.Collections;
import java.util.List;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;

/**
 *
 * @author dika
 */
public class SparepartReport extends DataReport {

    private List<Sparepart> spareparts = Collections.emptyList();

    public SparepartReport() {
        super("Laporan Data Sparepart");
    }

    public void setSpareparts(List<Sparepart> spareparts) {
        this.spareparts = spareparts;
    }

    @Override
    protected DRDataSource createDataSource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID", "id", type.integerType());
        TextColumnBuilder<?> namaCol = createColumn("Nama", "nama", type.stringType());
        TextColumnBuilder<?> jenkelCol = createColumn("Jenis Kelamin", "jenkel", type.stringType());
        TextColumnBuilder<?> noTelpCol = createColumn("No. Telp", "noTelp", type.stringType());
        TextColumnBuilder<?> noKtpCol = createColumn("No. KTP", "noKtp", type.stringType());
        TextColumnBuilder<?> alamatCol = createColumn("Alamat", "alamat", type.stringType());

        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, namaCol, jenkelCol, noTelpCol, noKtpCol, alamatCol);

    }

}
