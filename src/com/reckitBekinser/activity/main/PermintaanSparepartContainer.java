package com.reckitBekinser.activity.main;

import com.dika.util.CollectionHelper;
import com.dika.view.InputContainer;
import com.dika.view.component.Button;
import com.dika.view.component.Panel;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;

import javax.swing.text.JTextComponent;
import java.util.List;

public interface PermintaanSparepartContainer extends InputContainer<Panel> {
    TextField getIdKaryawanPermintaanField();
    TextField getNamaKaryawanPermintaanField();
    PagingTableView getPagingTableView();

    Button getSearchButton();
    Button getAddButton();
    Button getSaveButton();
    Button getClearButton();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getIdKaryawanPermintaanField(),
                getNamaKaryawanPermintaanField()
        );
    }
}
