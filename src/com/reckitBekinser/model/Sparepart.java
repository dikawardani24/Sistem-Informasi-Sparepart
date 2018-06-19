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
@Table(name = "sparepart")
@XmlRootElement
@NamedQueries(
        @NamedQuery(name = "Sparepart.findByName", query = "SELECT s FROM Sparepart s WHERE s.nama = :nama")
)
public class Sparepart extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsparepart")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    @Basic(optional = false)
    @Column(name = "kategori")
    private String kategori;

    @Basic(optional = false)
    @Column(name = "stock")
    private int stock;

    @Basic(optional = false)
    @Column(name = "no_rak")
    private String noRak;

    @Basic(optional = false)
    @Column(name = "level_rak")
    private String levelRak;

    @Basic(optional = false)
    @Column(name = "keterangan")
    private String keterangan;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getNoRak() {
        return noRak;
    }

    public void setNoRak(String noRak) {
        this.noRak = noRak;
    }

    public String getLevelRak() {
        return levelRak;
    }

    public void setLevelRak(String levelRak) {
        this.levelRak = levelRak;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
        return super.hashCode() + 6;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof Sparepart)) return false;

        Sparepart otherSparepart = (Sparepart) other;
        return id.equals(otherSparepart.id) &&
                nama.equals(otherSparepart.nama) &&
                kategori.equals(otherSparepart.kategori) &&
                stock == otherSparepart.stock &&
                noRak.equals(otherSparepart.noRak) &&
                levelRak.equals(otherSparepart.levelRak) &&
                keterangan.equals(otherSparepart.keterangan);
    }
}
