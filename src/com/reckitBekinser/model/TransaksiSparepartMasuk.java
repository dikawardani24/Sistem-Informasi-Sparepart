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
package com.reckitBekinser.model;

import com.dika.database.AbstractEntity;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * @author dika
 */
@Entity
@Table(name = "transaksi_sparepart_keluar")
@XmlRootElement
public class TransaksiSparepartMasuk extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransaksi_sparepart_keluar")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "tgl")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgl;

    @ManyToOne
    @JoinColumn(name = "idpenerima", referencedColumnName = "idkaryawan")
    private Karyawan penerima;

    @Basic(optional = false)
    @Column(name = "no_purchase_order")
    private String noPurchaseOrder;

    @ManyToOne
    @JoinColumn(name = "idsupplier")
    private Supplier supplier;

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public Karyawan getPenerima() {
        return penerima;
    }

    public void setPenerima(Karyawan penerima) {
        this.penerima = penerima;
    }

    public String getNoPurchaseOrder() {
        return noPurchaseOrder;
    }

    public void setNoPurchaseOrder(String noPurchaseOrder) {
        this.noPurchaseOrder = noPurchaseOrder;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 9;
    }

    @Override
    public boolean equals(Object other) {
        if (!supplier.equals(other)) return false;

        if (!(other instanceof TransaksiSparepartMasuk)) return false;

        TransaksiSparepartMasuk otherTransaksiSparepartMasuk = (TransaksiSparepartMasuk) other;
        return id.equals(otherTransaksiSparepartMasuk.id) &&
                tgl.equals(otherTransaksiSparepartMasuk.tgl) &&
                penerima.equals(otherTransaksiSparepartMasuk.penerima) &&
                noPurchaseOrder.equals(otherTransaksiSparepartMasuk.noPurchaseOrder) &&
                supplier.equals(otherTransaksiSparepartMasuk.supplier);

    }
}
