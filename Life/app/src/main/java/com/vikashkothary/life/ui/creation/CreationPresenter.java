package com.vikashkothary.life.ui.creation;

import com.vikashkothary.life.data.DataManager;
import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.ui.base.BasePresenter;
import com.vikashkothary.life.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by vikash on 19/07/17.
 */

public class CreationPresenter extends BasePresenter<CreationMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public CreationPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(CreationMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadReminders() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getReminders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Reminder>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the reminders.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Reminder> reminders) {
                        if (reminders.isEmpty()) {
                            getMvpView().showRemindersEmpty();
                        } else {
                            getMvpView().showReminders(reminders);
                        }
                    }
                });
    }

}
