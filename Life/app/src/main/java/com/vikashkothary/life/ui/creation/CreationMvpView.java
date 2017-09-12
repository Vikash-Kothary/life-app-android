package com.vikashkothary.life.ui.creation;

import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.ui.base.MvpView;

import java.util.List;

public interface CreationMvpView extends MvpView {

    void showReminders(List<Reminder> reminders);

    void showRemindersEmpty();

    void showError();

}
