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

/**
 *
 * @author dika
 */
@Entity
@Table(name = "detail_transaksi_sparepart_masuk")
@XmlRootElement
public class DetailTransaksiSparepartMasuk extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetail_transaksi_sparepart_masuk")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idsparepart")
    private Sparepart sparepart;

    @ManyToOne
    @JoinColumn(name = "idtransaksi_sparepart_masuk")
    private TransaksiSparepartMasuk transaksiSparepartMasuk;

    @Basic(optional = false)
    @Column(name = "jumlah")
    private int jumlah;

    public Sparepart getSparepart() {
        return sparepart;
    }

    public void setSparepart(Sparepart sparepart) {
        this.sparepart = sparepart;
    }

    public TransaksiSparepartMasuk getTransaksiSparepartMasuk() {
        return transaksiSparepartMasuk;
    }

    public void setTransaksiSparepartMasuk(TransaksiSparepartMasuk transaksiSparepartMasuk) {
        this.transaksiSparepartMasuk = transaksiSparepartMasuk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
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
        return super.hashCode() + 3;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof DetailTransaksiSparepartMasuk)) return false;

        DetailTransaksiSparepartMasuk otherSparepartKeluar = (DetailTransaksiSparepartMasuk) other;
        return id.equals(otherSparepartKeluar.id) &&
                sparepart.equals(otherSparepartKeluar.sparepart) &&
                transaksiSparepartMasuk.equals(otherSparepartKeluar.transaksiSparepartMasuk) &&
                jumlah == otherSparepartKeluar.jumlah;
    }
}
