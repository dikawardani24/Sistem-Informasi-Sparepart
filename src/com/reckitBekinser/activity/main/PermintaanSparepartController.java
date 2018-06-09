package com.reckitBekinser.activity.main;

import com.dika.Logger;
import com.dika.view.component.Button;
import com.dika.view.component.Panel;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;
import com.reckitBekinser.activity.AdminActivity;
import com.reckitBekinser.model.DetailPermintaanSparepart;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.model.PermintaanSparepart;
import com.reckitBekinser.model.Sparepart;
import com.reckitBekinser.service.DetailPermintaanSparepartServiceImpl;
import com.reckitBekinser.service.KaryawanServiceImpl;
import com.reckitBekinser.service.PermintaanSparepartServiceImpl;
import com.reckitBekinser.tableModel.DetailPermintaanSparepartTableModel;

import java.util.List;

public class PermintaanSparepartController extends MainController
    implements PermintaanSparepartContainer {
    private final PermintaanSparepartContainer container = new PermintaanSparepartContainerImpl();
    private Karyawan teknisi;
    private DetailPermintaanSparepartTableModel tableModel;

    public PermintaanSparepartController(AdminActivity mainActivity) {
        super(mainActivity, "Penerimaan Controller");
    }

    private void findKaryawan() {
        if (!validateIdKaryawan()) return;

        String idString = getIdKaryawanPermintaanField().getText();
        int id = Integer.parseInt(idString);

        teknisi = execute(new KaryawanServiceImpl(), karyawanService -> {
            try {
                return karyawanService.findBy(id);
            } catch (Exception e) {
                return null;
            }
        });

        if (teknisi == null) {
            showFailed("Tidak Menemukan Karyawan Dengan ID : "+id);
            return;
        }

        getNamaKaryawanPermintaanField().setText(teknisi.getNama());
    }

    private void addRequestSparepart() {

    }

    private void saveRequestSparepart() {
        if (!validateAllInput()) return;

        PermintaanSparepart permintaanSparepart = new PermintaanSparepart();
        permintaanSparepart.setTeknisi(teknisi);
        permintaanSparepart.setTgl(today());

        boolean permintaanSaved = execute(new PermintaanSparepartServiceImpl(), permintaanSparepartService -> {
            try {
                permintaanSparepartService.create(permintaanSparepart);
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        if (permintaanSaved) {
            savaDetail(permintaanSparepart);
        } else {
            showFailed("Tidak Dapat Menyimpan Data Permintaan Sparepart Anda");
        }
    }

    private void savaDetail(PermintaanSparepart permintaanSparepart) {
        List<DetailPermintaanSparepart> detailPermintaanSpareparts = tableModel.getEntities();
        boolean detailSaved = false;
        for (DetailPermintaanSparepart detailPermintaanSparepart : detailPermintaanSpareparts) {
            detailPermintaanSparepart.setPermintaanSparepart(permintaanSparepart);
            detailSaved = isSaved(detailPermintaanSparepart);

            if (!detailSaved) {
                Sparepart sparepart = detailPermintaanSparepart.getSparepart();
                showFailed("Tidak Dapat Menyimpan Detail Sparepart : "+sparepart.getNama()+ " Pada Permintaan Anda");
                break;
            }
        }

        if (detailSaved) showSucceed("Data Permintaan Anda Berhasil Disimpan");
    }

    private boolean isSaved(DetailPermintaanSparepart detailPermintaanSparepart) {
        return execute(new DetailPermintaanSparepartServiceImpl(), detailPermintaanSparepartService -> {
            try {
                detailPermintaanSparepartService.create(detailPermintaanSparepart);
                return true;
            } catch (Exception e) {
                Logger.INSTANCE.printError(e);
                return false;
            }
        });
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean validateIdKaryawan() {
        String idKaryawanString = getIdKaryawanPermintaanField().getText();
        if (idKaryawanString.isEmpty()) {
            showEmptyNotifOn(getIdKaryawanPermintaanField());
            return false;
        }

        try {
            Integer.parseInt(idKaryawanString);
            return true;
        } catch (NumberFormatException e) {
            Logger.INSTANCE.printError(e);
            showNotifOn(getIdKaryawanPermintaanField(), "ID Karyawan Tidak Valid");
            return false;
        }
    }

    private boolean validateAllInput() {
        if (!validateIdKaryawan()) return false;

        if (teknisi == null) {
            showNotifOn(getSearchButton(), "Tekan Tombol Ini Untuk Mencari Data Anda");
            return false;
        }

        List<DetailPermintaanSparepart> detailPermintaanSpareparts = tableModel.getEntities();
        if (detailPermintaanSpareparts.isEmpty()) {
            showInfo("Data Sparepart Yang Dipinjam Masih Kosong");
            return false;
        }
        return true;
    }

    @Override
    public void init() {
        getSearchButton().addActionListener(e -> findKaryawan());
        getAddButton().addActionListener(e -> addRequestSparepart());
        getSaveButton().addActionListener(e -> saveRequestSparepart());
        getClearButton().addActionListener(e -> clear());
    }

    @Override
    public TextField getIdKaryawanPermintaanField() {
        return container.getIdKaryawanPermintaanField();
    }

    @Override
    public TextField getNamaKaryawanPermintaanField() {
        return container.getNamaKaryawanPermintaanField();
    }

    @Override
    public PagingTableView getPagingTableView() {
        return container.getPagingTableView();
    }

    @Override
    public Button getSearchButton() {
        return container.getSearchButton();
    }

    @Override
    public Button getAddButton() {
        return container.getAddButton();
    }

    @Override
    public Button getSaveButton() {
        return container.getSaveButton();
    }

    @Override
    public Button getClearButton() {
        return container.getClearButton();
    }

    @Override
    public Panel getRoot() {
        return container.getRoot();
    }
}
