package com.reckitBekinser.activity.main;

import com.dika.Logger;
import com.dika.activity.Activity;
import com.dika.database.DatabaseService;
import com.dika.report.Report;
import com.dika.util.CalendarHelper;
import com.reckitBekinser.activity.MainActivity;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;

abstract public class MainController {
    private final MainActivity mainActivity;
    private final String controllerTitle;

    MainController(MainActivity mainActivity, String controllerTitle) {
        this.mainActivity = mainActivity;
        this.controllerTitle = controllerTitle;
    }

    abstract public void init();

    public String getControllerTitle() {
        return controllerTitle;
    }

    protected MainActivity getMainActivity() {
        return mainActivity;
    }

    protected void showInfo(String message) {
        mainActivity.showInfo(this, message);
    }

    protected void showFailed(String message) {
        mainActivity.showFailed(this, message);
    }

    protected void showSucceed(String message) {
        Logger.INSTANCE.printInfo("Telling succeed on "+getClass().getSimpleName());
        mainActivity.showSucceed(this, message);
    }

    protected void showNotifOn(Component component, String message) {
        mainActivity.showNotifOn(component, message);
    }

    protected void showEmptyNotifOn(Component component) {
        mainActivity.showEmptyNotifOn(this, component);
    }

    public String todayString() {
        CalendarHelper helper = CalendarHelper.INSTANCE;
        return helper.dateToLocal(helper.today());
    }

    public Date today() {
        CalendarHelper helper = CalendarHelper.INSTANCE;
        return helper.today();
    }

    protected   <R, S extends DatabaseService<?, ?>> R execute(S dbService, Function1<? super S, ? extends R> block) {
        return mainActivity.execute(this, dbService, block);
    }

    protected   <R, S extends DatabaseService<?, ?>> R execute(S dbService, String onSucceed, String onFailed,Function1<? super S, ? extends R> block) {
        return mainActivity.execute(this, dbService, onSucceed, onFailed, block);
    }

    protected void showReport(Report report) {
        mainActivity.showReport(this, report);
    }

    @NotNull
    protected <A extends Activity<?>> A startOther(Class<A> activityClass) {
        return mainActivity.startOther(this, activityClass);
    }
}
