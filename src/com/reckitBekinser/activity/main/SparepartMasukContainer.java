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
package com.reckitBekinser.activity.main;

import com.alee.laf.menu.WebMenuItem;
import com.dika.util.CollectionHelper;
import com.dika.view.InputContainer;
import com.dika.view.component.Button;
import com.dika.view.component.Panel;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;

import javax.swing.text.JTextComponent;
import java.util.List;

/**
 *
 * @author dika
 */
public interface SparepartMasukContainer extends InputContainer<Panel> {
    TextField getNoPurchaseOrderField();
    TextField getNamaSupplierField();

    Button getChooseSupplierButton();
    Button getSeeDetailSupplierButton();
    Button getAddSparepartButton();
    Button getSaveButton();
    Button getClearButton();

    WebMenuItem getRemoveSparepartMenuItem();
    WebMenuItem getChangeJumlahSparepartMenuItem();

    PagingTableView getPagingTableView();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getNoPurchaseOrderField(),
                getNamaSupplierField()
        );
    }
}
