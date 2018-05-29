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
@Table(name = "supplier")
@XmlRootElement
public class Supplier extends AbstractEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsupplier")
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    @Basic(optional = false)
    @Column(name = "no_telp1")
    private String noTelp1;

    @Basic(optional = false)
    @Column(name = "no_telp2")
    private String noTelp2;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "keterangan")
    private String keterangan;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelp1() {
        return noTelp1;
    }

    public void setNoTelp1(String noTelp1) {
        this.noTelp1 = noTelp1;
    }

    public String getNoTelp2() {
        return noTelp2;
    }

    public void setNoTelp2(String noTelp2) {
        this.noTelp2 = noTelp2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return super.hashCode() + 7;
    }

    @Override
    public boolean equals(Object other) {
        if (!super.equals(other)) return false;

        if (!(other instanceof Supplier)) return false;

        Supplier otherSupplier = (Supplier) other;
        return id.equals(otherSupplier.id) &&
                nama.equals(otherSupplier.nama)&&
                noTelp1.equals(otherSupplier.noTelp1)&&
                noTelp2.equals(otherSupplier.noTelp2)&&
                email.equals(otherSupplier.email)&&
                keterangan.equals(otherSupplier.keterangan);
    }
}
