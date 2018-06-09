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
package com.reckitBekinser.activity;

import com.dika.view.component.Frame;

import javax.swing.*;

/**
 *
 * @author dika
 */
public class MainViewImpl extends Frame implements MainView{

    /**
     * Creates new form MainViewImpl
     */
    MainViewImpl() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        com.dika.view.component.MenuBar menuBar = new com.dika.view.component.MenuBar();
        javax.swing.JMenu programMenu = new javax.swing.JMenu();
        changeUsernameMenu = new javax.swing.JMenuItem();
        changePasswordMenu = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator1 = new javax.swing.JPopupMenu.Separator();
        logoutMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        dataManagerMenu = new javax.swing.JMenu();
        mdSparepartMenu = new javax.swing.JMenuItem();
        mdSupplierMenu = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mdKaryawanMenu = new javax.swing.JMenuItem();
        mdUserMenu = new javax.swing.JMenuItem();
        javax.swing.JMenu aboutMenu = new javax.swing.JMenu();
        abProgramMenu = new javax.swing.JMenuItem();
        abLaundryMenu = new javax.swing.JMenuItem();
        abUnivMenu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rental Mobil");

        programMenu.setText("Program");

        changeUsernameMenu.setText("Ganti Username");
        programMenu.add(changeUsernameMenu);

        changePasswordMenu.setText("Ganti Password");
        programMenu.add(changePasswordMenu);
        programMenu.add(jSeparator1);

        logoutMenu.setText("Logout");
        programMenu.add(logoutMenu);

        exitMenu.setText("Keluar");
        programMenu.add(exitMenu);

        menuBar.add(programMenu);

        dataManagerMenu.setText("Data Manager");

        mdSparepartMenu.setText("Manager Data Sparepart");
        dataManagerMenu.add(mdSparepartMenu);

        mdSupplierMenu.setText("Manager Data Supplier");
        dataManagerMenu.add(mdSupplierMenu);
        dataManagerMenu.add(jSeparator2);

        mdKaryawanMenu.setText("Manager Data Karyawan");
        dataManagerMenu.add(mdKaryawanMenu);

        mdUserMenu.setText("Manager Data User");
        dataManagerMenu.add(mdUserMenu);

        menuBar.add(dataManagerMenu);

        aboutMenu.setText("Tentang");

        abProgramMenu.setText("Tentang Program");
        aboutMenu.add(abProgramMenu);

        abLaundryMenu.setText("Tentang Laundry");
        aboutMenu.add(abLaundryMenu);

        abUnivMenu.setText("Tentang Universitas");
        aboutMenu.add(abUnivMenu);

        menuBar.add(aboutMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abLaundryMenu;
    private javax.swing.JMenuItem abProgramMenu;
    private javax.swing.JMenuItem abUnivMenu;
    private javax.swing.JMenuItem changePasswordMenu;
    private javax.swing.JMenuItem changeUsernameMenu;
    private javax.swing.JMenu dataManagerMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenuItem logoutMenu;
    private javax.swing.JMenuItem mdKaryawanMenu;
    private javax.swing.JMenuItem mdSparepartMenu;
    private javax.swing.JMenuItem mdSupplierMenu;
    private javax.swing.JMenuItem mdUserMenu;
    // End of variables declaration//GEN-END:variables

    @Override
    public JMenu getDataManagerMenu() {
        return dataManagerMenu;
    }

    @Override
    public JMenuItem getAbLaundryMenu() {
        return abLaundryMenu;
    }

    @Override
    public JMenuItem getAbProgramMenu() {
        return abProgramMenu;
    }

    @Override
    public JMenuItem getAbUnivMenu() {
        return abUnivMenu;
    }

    @Override
    public JMenuItem getChangePasswordMenu() {
        return changePasswordMenu;
    }

    @Override
    public JMenuItem getChangeUsernameMenu() {
        return changeUsernameMenu;
    }

    @Override
    public JMenuItem getExitMenu() {
        return exitMenu;
    }

    @Override
    public JMenuItem getLogoutMenu() {
        return logoutMenu;
    }

    @Override
    public JMenuItem getMdKaryawanMenu() {
        return mdKaryawanMenu;
    }

    @Override
    public JMenuItem getMdUserMenu() {
        return mdUserMenu;
    }

    @Override
    public JMenuItem getMdSparepartMenu() {
        return mdSparepartMenu;
    }

    @Override
    public JMenuItem getMdSupplierMenu() {
        return mdSupplierMenu;
    }

    @Override
    public Frame getRoot() {
        return this;
    }
}
