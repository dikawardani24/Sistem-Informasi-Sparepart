package com.reckitBekinser.activity.menuDataManager.sparepart;

import com.dika.util.CollectionHelper;
import com.dika.view.InputView;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;

import javax.swing.text.JTextComponent;
import java.util.List;

interface UpdateSparepartView extends InputView<Dialog> {
    TextField getNamaSparepartField();

    TextField getKategoriSparepartField();

    TextField getJumlahSparepartField();

    TextField getNoRakSparepartField();

    TextField getLevelRakSparepartField();

    TextArea getKeteranganSparepartField();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getNamaSparepartField(),
                getKategoriSparepartField(),
                getJumlahSparepartField(),
                getNoRakSparepartField(),
                getLevelRakSparepartField(),
                getKeteranganSparepartField()
        );
    }
}
