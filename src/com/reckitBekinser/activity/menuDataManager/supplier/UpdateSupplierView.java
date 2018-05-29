package com.reckitBekinser.activity.menuDataManager.supplier;

import com.dika.util.CollectionHelper;
import com.dika.view.InputView;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextArea;
import com.dika.view.component.TextField;

import javax.swing.text.JTextComponent;
import java.util.List;

public interface UpdateSupplierView extends InputView<Dialog> {
    TextField getNamaSupplierField();
    TextField getNoTelp1SupplierField();
    TextField getNoTelp2SupplierField();
    TextField getEmailSupplierField();
    TextArea getKeteranganSupplierField();

    Button getSaveButton();
    Button getCancelButton();
    Button getClearButton();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getNamaSupplierField(),
                getNoTelp1SupplierField(),
                getNoTelp2SupplierField(),
                getEmailSupplierField(),
                getKeteranganSupplierField()
        );
    }
}
