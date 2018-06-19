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
package com.reckitBekinser.service;

import com.dika.database.DatabaseService;
import com.reckitBekinser.model.Sparepart;
import java.util.List;

/**
 *
 * @author dika
 */
public interface SparepartService extends DatabaseService<Integer, Sparepart> {
    List<Sparepart> findsBy(String nama);
}
