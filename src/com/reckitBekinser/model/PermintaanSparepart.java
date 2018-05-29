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
@Table(name = "permintaan_sparepart")
@XmlRootElement
public class PermintaanSparepart extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpermintaan_sparepart")
    @Basic(optional = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idteknisi", referencedColumnName = "idkaryawan")
    private Karyawan teknisi;

    @Basic(optional = false)
    @Column(name = "tgl")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tgl;

    public Karyawan getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(Karyawan teknisi) {
        this.teknisi = teknisi;
    }

    public Date getTgl() {
        return tgl;
    }

    public void setTgl(Date tgl) {
        this.tgl = tgl;
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
        return super.hashCode()+5;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof PermintaanSparepart)) return false;

        PermintaanSparepart otherPermintaanSparepart = (PermintaanSparepart) other;
        return id.equals(otherPermintaanSparepart.id) &&
                teknisi.equals(otherPermintaanSparepart.teknisi) &&
                tgl.equals(otherPermintaanSparepart.tgl);
    }
}
