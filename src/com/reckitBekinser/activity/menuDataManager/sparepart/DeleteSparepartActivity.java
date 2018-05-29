package com.reckitBekinser.activity.menuDataManager.sparepart;

import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;
import com.reckitBekinser.activity.menuDataManager.SparepartManagerActivity;
import com.reckitBekinser.model.Sparepart;
import com.reckitBekinser.service.SparepartServiceImpl;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DeleteSparepartActivity extends InputActivity<DeleteSparepartView>
    implements DeleteSparepartView{
    private final DeleteSparepartView view = new DeleteSparepartViewImpl();
    private Sparepart sparepart;

    public void setSparepart(Sparepart sparepart) {
        SwingUtilities.invokeLater(() -> {
            this.sparepart = sparepart;
            getNamaSparepartField().setText(sparepart.getNama());
            getKategoriSparepartField().setText(sparepart.getKategori());
            getJumlahSparepartField().setValue(sparepart.getJumlah());
            getNoRakSparepartField().setText(sparepart.getNoRak());
            getLevelRakSparepartField().setText(sparepart.getLevelRak());
            getKeteranganSparepartField().setText(sparepart.getKeterangan());
        });
    }

    private void delete() {
        if (sparepart == null) {
            showFailed("Tidak Ada Data Sparepart Untuk Dihapus");
            return;
        }

        execute(new SparepartServiceImpl(), sparepartService -> {
            try {
                sparepartService.destroy(sparepart);
                Activity<?> parent = getParent();
                if (parent instanceof SparepartManagerActivity) {
                    ((SparepartManagerActivity) parent).refresh();
                }
                showSucceed("Data Sparepart Berhasil Dihapus");
                stop();
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                showFailed("Data Sparepart Gagal Dihapus");
            }
            return Unit.INSTANCE;
        });
    }

    @NotNull
    @Override
    public DeleteSparepartView getView() {
        return view;
    }

    @Override
    protected void initListener(DeleteSparepartView deleteSparepartView) {
        getCancelButton().addActionListener(e -> stop());
        getDeleteButton().addActionListener(e -> delete());
    }

    @Override
    public TextField getNamaSparepartField() {
        return view.getNamaSparepartField();
    }

    @Override
    public TextField getKategoriSparepartField() {
        return view.getKategoriSparepartField();
    }

    @Override
    public JFormattedTextField getJumlahSparepartField() {
        return view.getJumlahSparepartField();
    }

    @Override
    public TextField getNoRakSparepartField() {
        return view.getNoRakSparepartField();
    }

    @Override
    public TextField getLevelRakSparepartField() {
        return view.getLevelRakSparepartField();
    }

    @Override
    public TextArea getKeteranganSparepartField() {
        return view.getKeteranganSparepartField();
    }

    @Override
    public Button getCancelButton() {
        return view.getCancelButton();
    }

    @Override
    public Button getDeleteButton() {
        return view.getDeleteButton();
    }

    @Override
    public Dialog getRoot() {
        return view.getRoot();
    }
}
