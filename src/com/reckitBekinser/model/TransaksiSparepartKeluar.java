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
 *
 * @author dika
 */
@Entity
@Table(name = "transaksi_sparepart_keluar")
@XmlRootElement
public class TransaksiSparepartKeluar extends AbstractEntity<Integer> {
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
    @JoinColumn(name = "idpemberi_izin", referencedColumnName = "idkaryawan")
    private Karyawan pemberiIzin;

    @OneToOne
    @JoinColumn(name = "idpermintaan_sparepart")
    private PermintaanSparepart permintaanSparepart;

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
    }

    public Karyawan getPemberiIzin() {
        return pemberiIzin;
    }

    public void setPemberiIzin(Karyawan pemberiIzin) {
        this.pemberiIzin = pemberiIzin;
    }

    public PermintaanSparepart getPermintaanSparepart() {
        return permintaanSparepart;
    }

    public void setPermintaanSparepart(PermintaanSparepart permintaanSparepart) {
        this.permintaanSparepart = permintaanSparepart;
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
        return super.hashCode() + 8;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof TransaksiSparepartKeluar)) return false;

        TransaksiSparepartKeluar otherTransaksiSparepartKeluar = (TransaksiSparepartKeluar) other;
        return id.equals(otherTransaksiSparepartKeluar.id)&&
                tgl.equals(otherTransaksiSparepartKeluar.tgl)&&
                pemberiIzin.equals(otherTransaksiSparepartKeluar.pemberiIzin)&&
                permintaanSparepart.equals(otherTransaksiSparepartKeluar.permintaanSparepart);
    }
}
