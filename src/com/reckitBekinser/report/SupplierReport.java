package com.reckitBekinser.report;

import com.dika.report.DataReport;
import com.dika.util.CollectionHelper;
import com.reckitBekinser.model.Supplier;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.type;

public final class SupplierReport extends DataReport {
    private List<Supplier> suppliers;

    public SupplierReport() {
        super("Laporan Data Supplier");
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    @NotNull
    @Override
    protected DRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "nama", "noTelp1", "noTelp2",
                "email", "keterangan");

        suppliers.forEach(sparepart -> dataSource.add(
                sparepart.getId(),
                sparepart.getNama(),
                sparepart.getNoTelp1(),
                sparepart.getNoTelp2(),
                sparepart.getEmail(),
                sparepart.getKeterangan()
        ));

        return dataSource;
    }

    @NotNull
    @Override
    protected List<TextColumnBuilder<?>> createColumns() {
        TextColumnBuilder<?> idCol = createColumn("ID", "id", type.integerType());
        TextColumnBuilder<?> namaCol = createColumn("Nama", "nama", type.stringType());
        TextColumnBuilder<?> kategoriCol = createColumn("No. Telp1", "noTelp1", type.stringType());
        TextColumnBuilder<?> jumlahCol = createColumn("No. Telp2", "noTelp2", type.integerType());
        TextColumnBuilder<?> noRakCol = createColumn("Email", "email", type.stringType());
        TextColumnBuilder<?> keteranganCol = createColumn("Keterangan", "keterangan", type.stringType());

        return CollectionHelper.INSTANCE
                .collectAsArrayList(idCol, namaCol, kategoriCol, jumlahCol, noRakCol, keteranganCol);

    }
}
