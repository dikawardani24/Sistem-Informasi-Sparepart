/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reckitBekinser.activity;

import com.dika.view.InputView;
import com.dika.view.component.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.text.JTextComponent;

/**
 *
 * @author dika
 */
public interface MainView extends InputView<Frame> {
    JMenu getDataManagerMenu();
    
    JMenuItem getAbLaundryMenu();

    JMenuItem getAbProgramMenu();

    JMenuItem getAbUnivMenu();

    JMenuItem getChangePasswordMenu();

    JMenuItem getChangeUsernameMenu();

    JMenuItem getExitMenu();

    JMenuItem getLogoutMenu();

    JMenuItem getMdKaryawanMenu();
    
    JMenuItem getMdUserMenu();

    @Override
    default List<JTextComponent> getTextComponents() {
        List<JTextComponent> textComponents = new ArrayList<>();
        
        return textComponents;
    }

}
