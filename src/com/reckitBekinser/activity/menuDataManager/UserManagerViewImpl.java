/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reckitBekinser.activity.menuDataManager;

import com.alee.laf.menu.WebMenuItem;
import com.dika.res.SmallIconRes;
import com.dika.view.component.Button;
import com.dika.view.component.Frame;
import com.dika.view.custom.PagingTableView;

import javax.swing.*;

/**
 *
 * @author dika
 */
public final class UserManagerViewImpl extends Frame implements UserManagerView {

    /**
     * Creates new form UserManagerViewImpl
     */
    UserManagerViewImpl() {
        initComponents();
        createContextMenuOnTable();
    }

    private void createContextMenuOnTable() {
        JPopupMenu tableDataMenu = new JPopupMenu("Table Menu");
        tableDataMenu.add(deleteMenuItem);
        pagingTableViewImpl1.getTable().setComponentPopupMenu(tableDataMenu);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        com.dika.view.component.Panel panel1 = new com.dika.view.component.Panel();
        com.dika.view.component.custom.HeaderLabel headerLabel1 = new com.dika.view.component.custom.HeaderLabel();
        pagingTableViewImpl1 = new com.dika.view.custom.PagingTableViewImpl();
        printButton1 = new com.dika.view.component.custom.PrintButton();
        addButton1 = new com.dika.view.component.custom.AddButton();

        setTitle("Manager Data User");

        headerLabel1.setText("Manager Data User");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagingTableViewImpl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagingTableViewImpl1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private final WebMenuItem deleteMenuItem = new WebMenuItem("Hapus", SmallIconRes.INSTANCE.getCancelIcon());
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dika.view.component.custom.AddButton addButton1;
    private com.dika.view.custom.PagingTableViewImpl pagingTableViewImpl1;
    private com.dika.view.component.custom.PrintButton printButton1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Button getAddNewKaryawanButton() {
        return addButton1;
    }

    @Override
    public Button getPrintButton() {
        return printButton1;
    }

    @Override
    public PagingTableView getPagingTableView() {
        return pagingTableViewImpl1;
    }

    @Override
    public WebMenuItem getDeletePaketMenuItem() {
        return deleteMenuItem;
    }

    @Override
    public Frame getRoot() {
        return this;
    }
}