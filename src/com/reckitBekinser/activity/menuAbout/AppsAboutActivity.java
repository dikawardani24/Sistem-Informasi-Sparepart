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
package com.reckitBekinser.activity.menuAbout;

import com.dika.activity.Activity;
import com.dika.res.HtmlTextRes;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public class AppsAboutActivity extends Activity<AppsAboutView> {
    private final AppsAboutView aboutView = new AppsAboutView();
    
    @NotNull
    @Override
    public AppsAboutView getView() {
        return aboutView;
    }

    @Override
    protected void initListener(AppsAboutView v) {
        aboutView.getDetailTextPane().setContentType(HtmlTextRes.INSTANCE.getType());
        aboutView.getDetailTextPane().setText(HtmlTextRes.INSTANCE.getAppAbout());
    }
}
