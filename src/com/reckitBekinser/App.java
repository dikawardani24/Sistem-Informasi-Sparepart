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
package com.reckitBekinser;

import com.dika.Logger;
import com.dika.System;
import com.reckitBekinser.activity.menuProgram.LoginActivity;
import kotlin.Unit;

/**
 *
 * @author dika
 */
public class App {
    public static void main(String [] args) {
        System.INSTANCE.boot(system -> {
            system.setCompanyName("PT. Reckti Bekinser");
            system.setPersitenceName("SparePartReckitBekinserPU");
            system.setAllowMultipleInstance(false);
            system.setLoggerType(Logger.LoggerType.FULL_VERBOSE);
            system.setFirstActivity(LoginActivity.class);
            return Unit.INSTANCE;
        });
    }
}
