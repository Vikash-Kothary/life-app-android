package com.vikashkothary.life.ui.main.stream;

import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.ui.base.MvpView;

import java.util.List;

public interface StreamMvpView extends MvpView {

    void showReminders(List<Reminder> reminders);

    void showRemindersEmpty();

    void showError();

}
