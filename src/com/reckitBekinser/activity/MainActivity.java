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

import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.activity.service.OnStartedAction;
import com.dika.database.DatabaseService;
import com.dika.report.Report;
import com.dika.view.component.Frame;
import com.dika.view.component.MenuBar;
import com.reckitBekinser.Session;
import com.reckitBekinser.activity.main.MainController;
import com.reckitBekinser.activity.main.PermintaanSparepartContainerImpl;
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
import com.reckitBekinser.model.Karyawan;
import java.awt.Component;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 *
 * @author dika
 */
public final class MainActivity extends InputActivity<MainView> implements MainView {
    private final MainView view = new MainViewImpl();

    private void switchView() {
        Karyawan karyawan = Session.getInstance().getKaryawan();
        switch(karyawan.getJabatan()) {
            case "Admin" :
                if (!isContainDataManager()) {
                    getMainMenuBar().add(getDataManagerMenu(), 1);
                }

                getDataManagerMenu().setVisible(true);
                view.getRoot().setContentPane(getAdmintTabbedPane());
                break;
            case "Teknisi" :
                getMainMenuBar().remove(getDataManagerMenu());
                view.getRoot().setContentPane(new PermintaanSparepartContainerImpl());
                break;
        }
    }

    private boolean isContainDataManager() {
        for (Component component : getMainMenuBar().getComponents()) {
            if (component.equals(getDataManagerMenu())) {
                return true;
            }
        }

        return false;
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

        add((OnStartedAction) activity -> switchView());

        add((OnStartedAction) activity -> switchView());
    }

    @NotNull
    @Override
    public MainView getView() {
        return view;
    }

    public void showInfo(MainController mainController, String message) {
        Logger.INSTANCE.printInfo("Show Information Dialog From "+mainController.getControllerTitle());
        showInfo(message);
    }

    public void showFailed(MainController mainController, String message) {
        Logger.INSTANCE.printInfo("Show Failed Dialog From "+mainController.getControllerTitle());
        showFailed(message);
    }

    public void showSucceed(MainController mainController, String message) {
        Logger.INSTANCE.printInfo("Show Succeed Dialog From "+mainController.getControllerTitle());
        showSucceed(message);
    }

    public void showNotifOn(MainController mainController, Component component, String message) {
        Logger.INSTANCE.printInfo("Show Notification Dialog From "+mainController.getControllerTitle());
        showNotifOn(component, message);
    }

    public void showEmptyNotifOn(MainController mainController, Component component) {
        Logger.INSTANCE.printInfo("Show Empty Dialog From "+mainController.getControllerTitle());
        showEmptyNotifOn(component);
    }
    
    public  <R, S extends DatabaseService<?, ?>> R execute(MainController mainController, S dbService, Function1<? super S, ? extends R> block) {
        Logger.INSTANCE.printInfo("Using Database Service From "+mainController.getControllerTitle());
        return super.execute(dbService, block);
    }

    public  <R, S extends DatabaseService<?, ?>> R execute(MainController mainController, S dbService, String onSucceed, String onFailed,
                                                           Function1<? super S, ? extends R> block) {
        Logger.INSTANCE.printInfo("Using Database Service From "+mainController.getControllerTitle());
        return super.execute(dbService, onSucceed, onFailed, block);
    }

    public void showReport(MainController mainController, Report report) {
        Logger.INSTANCE.printInfo("Show Report From "+mainController.getControllerTitle());
        showReport(report);
    }

    @NotNull
    public <A extends Activity<?>> A startOther(MainController mainController, Class<A> activityClass) {
        Logger.INSTANCE.printInfo("Start Other Activity From "+mainController.getControllerTitle());
        return super.startOther(activityClass);
    }

    @Override
    public MenuBar getMainMenuBar() {
        return view.getMainMenuBar();
    }

    @Override
    public JTabbedPane getAdmintTabbedPane() {
        return view.getAdmintTabbedPane();
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
