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
 * @author dika
 */
@Entity
@Table(name = "detail_permintaan_sparepart")
@XmlRootElement
@NamedQueries(
        @NamedQuery(name = "DetailPermintaanSparepart.findByPermintaan", 
                query = "SELECT d FROM DetailPermintaanSparepart d WHERE d.permintaanSparepart.id= :idPermintaan")
)
public class DetailPermintaanSparepart extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetail_permintaan_sparepart")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idteknisi", referencedColumnName = "idkaryawan")
    private Karyawan teknisi;

    @ManyToOne
    @JoinColumn(name = "idsparepart")
    private Sparepart sparepart;

    @ManyToOne
    @JoinColumn(name = "idpermintaan_sparepart")
    private PermintaanSparepart permintaanSparepart;

    @Basic(optional = false)
    @Column(name = "jumlah")
    private int jumlah;

    public Karyawan getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(Karyawan teknisi) {
        this.teknisi = teknisi;
    }

    public Sparepart getSparepart() {
        return sparepart;
    }

    public void setSparepart(Sparepart sparepart) {
        this.sparepart = sparepart;
    }

    public PermintaanSparepart getPermintaanSparepart() {
        return permintaanSparepart;
    }

    public void setPermintaanSparepart(PermintaanSparepart permintaanSparepart) {
        this.permintaanSparepart = permintaanSparepart;
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
        return super.hashCode() + 1;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof DetailPermintaanSparepart)) return false;

        DetailPermintaanSparepart otherDetailPermintaanSparepart = (DetailPermintaanSparepart) other;
        return id.equals(otherDetailPermintaanSparepart.id) &&
                teknisi.equals(otherDetailPermintaanSparepart.teknisi) &&
                sparepart.equals(otherDetailPermintaanSparepart.sparepart) &&
                permintaanSparepart.equals(otherDetailPermintaanSparepart.permintaanSparepart) &&
                jumlah == otherDetailPermintaanSparepart.jumlah;
    }
}
