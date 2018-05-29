package com.reckitBekinser.activity.menuDataManager.user;

import com.dika.SystemException;
import com.dika.activity.Activity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import com.reckitBekinser.Session;
import com.reckitBekinser.activity.menuDataManager.UserManagerActivity;
import com.reckitBekinser.model.Karyawan;
import com.reckitBekinser.model.User;
import com.reckitBekinser.service.UserServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class DeleteUserActivity extends Activity<DeleteUserView> implements DeleteUserView {
    private final DeleteUserView deleteUserView = new DeleteUserViewImpl();
    private User user;

    public void setUser(User user) {
        SwingUtilities.invokeLater(()-> {
            this.user = user;
            Karyawan karyawan = user.getKaryawan();
            getIdKaryawanField().setText(String.valueOf(karyawan.getId()));
            getUsernameField().setText(user.getUsername());
            getNamaField().setText(karyawan.getNama());
            getJenkelField().setText(karyawan.getJenisKelamin());
        });
    }

    private void deleteUser() {
        if (user == null) {
            showInfo("Tidak ada data user untuk dihapus");
            return;
        }

        if (user.equals(Session.getInstance().getUser())) {
            showFailed("User ini sedang login, menghapus user ini tidak diijinkan");
            return;
        }

        execute(new UserServiceImpl(), userService -> {
            try {
                userService.destroy(user);
                Activity<?> parent = getParent();
                if (parent != null && parent instanceof UserManagerActivity) {
                    ((UserManagerActivity) parent).refresh();
                }
                showSucceed("Data user berhasil dihapus");
                stop();
            } catch (SystemException e) {
                showFailed("Data user gagal dihapus");
            }
            return userService;
        });
    }

    @Override
    protected void initListener(DeleteUserView deleteUserView) {
        getCancelButton().addActionListener(evt -> stop());
        getDeleteButton().addActionListener(evt -> deleteUser());
    }

    @NotNull
    @Override
    public DeleteUserView getView() {
        return deleteUserView;
    }

    @Override
    public TextField getIdKaryawanField() {
        return deleteUserView.getIdKaryawanField();
    }

    @Override
    public TextField getNamaField() {
        return deleteUserView.getNamaField();
    }

    @Override
    public TextField getJenkelField() {
        return deleteUserView.getJenkelField();
    }

    @Override
    public TextField getUsernameField() {
        return deleteUserView.getUsernameField();
    }

    @Override
    public Button getDeleteButton() {
        return deleteUserView.getDeleteButton();
    }

    @Override
    public Button getCancelButton() {
        return deleteUserView.getCancelButton();
    }

    @Override
    public Dialog getRoot() {
        return deleteUserView.getRoot();
    }
}
