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
package com.reckitBekinser.activity;

import com.dika.activity.Activity;
import com.dika.view.component.Frame;
import com.reckitBekinser.activity.menuAbout.AppsAboutActivity;
import com.reckitBekinser.activity.menuAbout.CompanyAboutActivity;
import com.reckitBekinser.activity.menuAbout.UnivAboutActivity;
import com.reckitBekinser.activity.menuDataManager.KaryawanManagerActivity;
import com.reckitBekinser.activity.menuDataManager.SparepartManagerActivity;
import com.reckitBekinser.activity.menuDataManager.SupplierManagerActivity;
import com.reckitBekinser.activity.menuDataManager.UserManagerActivity;
import com.reckitBekinser.activity.menuProgram.ChangePasswordActivity;
import com.reckitBekinser.activity.menuProgram.ChangeUsernameActivity;
import com.reckitBekinser.activity.menuProgram.LoginActivity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public final class MainActivity extends Activity<MainView> implements MainView {
    private final MainView view = new MainViewImpl();

    @NotNull
    @Override
    public MainView getView() {
        return view;
    }

    @Override
    protected void initListener(MainView v) {
        getAbLaundryMenu().addActionListener(e -> startOther(CompanyAboutActivity.class));
        getAbProgramMenu().addActionListener(e -> startOther(AppsAboutActivity.class));
        getAbUnivMenu().addActionListener(e -> startOther(UnivAboutActivity.class));

        getMdKaryawanMenu().addActionListener(e -> startOther(KaryawanManagerActivity.class));
        getMdUserMenu().addActionListener(e -> startOther(UserManagerActivity.class));
        getMdSparepartMenu().addActionListener(e -> startOther(SparepartManagerActivity.class));
        getMdSupplierMenu().addActionListener(e -> startOther(SupplierManagerActivity.class));

        getExitMenu().addActionListener(e -> System.exit(0));
        getChangePasswordMenu().addActionListener(e -> startOther(ChangePasswordActivity.class));
        getChangeUsernameMenu().addActionListener(e -> startOther(ChangeUsernameActivity.class));
        getLogoutMenu().addActionListener(e -> stopThenStart(LoginActivity.class));
    }

    @Override
    public JMenu getDataManagerMenu() {
        return view.getDataManagerMenu();
    }

    @Override
    public JMenuItem getAbLaundryMenu() {
        return view.getAbLaundryMenu();
    }

    @Override
    public JMenuItem getAbProgramMenu() {
        return view.getAbProgramMenu();
    }

    @Override
    public JMenuItem getAbUnivMenu() {
        return view.getAbUnivMenu();
    }

    @Override
    public JMenuItem getChangePasswordMenu() {
        return view.getChangePasswordMenu();
    }

    @Override
    public JMenuItem getChangeUsernameMenu() {
        return view.getChangeUsernameMenu();
    }

    @Override
    public JMenuItem getExitMenu() {
        return view.getExitMenu();
    }

    @Override
    public JMenuItem getLogoutMenu() {
        return view.getLogoutMenu();
    }

    @Override
    public JMenuItem getMdKaryawanMenu() {
        return view.getMdKaryawanMenu();
    }

    @Override
    public JMenuItem getMdUserMenu() {
        return view.getMdUserMenu();
    }

    @Override
    public JMenuItem getMdSparepartMenu() {
        return view.getMdSparepartMenu();
    }

    @Override
    public JMenuItem getMdSupplierMenu() {
        return view.getMdSupplierMenu();
    }

    @Override
    public Frame getRoot() {
        return view.getRoot();
    }
}
