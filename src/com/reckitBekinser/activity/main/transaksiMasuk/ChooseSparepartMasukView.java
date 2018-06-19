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
package com.reckitBekinser.activity.main.transaksiMasuk;

import com.alee.laf.menu.WebMenuItem;
import com.dika.util.CollectionHelper;
import com.dika.view.InputView;
import com.dika.view.component.Button;
import com.dika.view.component.Frame;
import com.dika.view.component.TextField;
import com.dika.view.custom.PagingTableView;

import javax.swing.text.JTextComponent;
import java.util.List;

/**
 *
 * @author dika
 */
public interface ChooseSparepartMasukView extends InputView<Frame> {
    TextField getNamaSparepartField();

    Button getSearchButton();

    PagingTableView getPagingTableView();

    WebMenuItem getChosenMenuItem();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getNamaSparepartField()
        );
    }
}
