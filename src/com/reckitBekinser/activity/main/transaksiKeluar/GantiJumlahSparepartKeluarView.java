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
package com.reckitBekinser.activity.main.transaksiKeluar;

import com.dika.util.CollectionHelper;
import com.dika.view.InputView;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.Label;
import com.dika.view.component.custom.DecimalFormattedTextField;
import java.util.List;
import javax.swing.text.JTextComponent;

/**
 *
 * @author dika
 */
public interface GantiJumlahSparepartKeluarView extends InputView<Dialog>{
    Label getJmlMaxLabel();
    DecimalFormattedTextField getJumlahField();

    Button getSaveButton();
    Button getCancelButton();
    
    @Override
    public default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getJumlahField()
        );
    }
}
