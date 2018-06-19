package com.reckitBekinser.activity.main.transaksiMasuk;

import com.alee.laf.menu.WebMenuItem;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Frame;
import com.dika.view.component.Table;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;
import com.dika.view.custom.PagingTableViewService;
import com.reckitBekinser.model.Sparepart;
import com.reckitBekinser.service.SparepartServiceImpl;
import com.reckitBekinser.tableModel.SparepartTableModel;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ChooseSparepartMasukActivity extends InputActivity<ChooseSparepartMasukView>
    implements ChooseSparepartMasukView, PagingTableViewService {
    private final ChooseSparepartMasukView view = new ChooseSparepartMasukViewImpl();
    private SparepartTableModel tableModel;

    private void cariDataSparepartByName() {
        if (!validateNamaSparepart()) {
            return;
        }

        List<Sparepart> sparepartList = execute(new SparepartServiceImpl(), service -> {
            try {
                return service.findsBy(getNamaSparepartField().getText());
            } catch (Exception e) {
                return Collections.emptyList();
            }
        });
    }

    private boolean validateNamaSparepart() {
        String namaSparepart = getNamaSparepartField().getText();
        if (namaSparepart.isEmpty()) {
            showNotifOn(getNamaSparepartField(), "Nama sparepart masih kosong");
            return false;
        }

        return true;
    }

    @Override
    public int countData() {
        return 0;
    }

    @Override
    public void insertData(int firstResult, int maxResults) {

    }

    @Override
    protected void initListener(ChooseSparepartMasukView chooseSparepartMasukView) {
        Table table = getPagingTableView().getTable();
        tableModel = new SparepartTableModel(table);
        getSearchButton().addActionListener(e -> cariDataSparepartByName());
    }

    @NotNull
    @Override
    public ChooseSparepartMasukView getView() {
        return view;
    }

    @Override
    public TextField getNamaSparepartField() {
        return view.getNamaSparepartField();
    }

    @Override
    public Button getSearchButton() {
        return view.getSearchButton();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return view.getPagingTableView();
    }

    @Override
    public WebMenuItem getChosenMenuItem() {
        return view.getChosenMenuItem();
    }

    @Override
    public Frame getRoot() {
        return view.getRoot();
    }

    @NotNull
    @Override
    public Activity<?> getActivity() {
        return this;
    }
}
