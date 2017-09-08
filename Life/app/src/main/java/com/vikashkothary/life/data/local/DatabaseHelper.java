package com.vikashkothary.life.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.data.model.Ribot;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder briteBuilder = new SqlBrite.Builder();
        mDb = briteBuilder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<Ribot> setRibots(final Collection<Ribot> newRibots) {
        return Observable.create(new Observable.OnSubscribe<Ribot>() {
            @Override
            public void call(Subscriber<? super Ribot> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.RibotProfileTable.TABLE_NAME, null);
                    for (Ribot ribot : newRibots) {
                        long result = mDb.insert(Db.RibotProfileTable.TABLE_NAME,
                                Db.RibotProfileTable.toContentValues(ribot.profile()),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(ribot);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Ribot>> getRibots() {
        return mDb.createQuery(Db.RibotProfileTable.TABLE_NAME,
                "SELECT * FROM " + Db.RibotProfileTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Ribot>() {
                    @Override
                    public Ribot call(Cursor cursor) {
                        return Ribot.create(Db.RibotProfileTable.parseCursor(cursor));
                    }
                });
    }

    public Observable<Reminder> setReminders(final Collection<Reminder> newReminders) {
        return Observable.create(new Observable.OnSubscribe<Reminder>() {
            @Override
            public void call(Subscriber<? super Reminder> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.RemindersTable.TABLE_NAME, null);
                    for (Reminder reminder : newReminders) {
                        long result = mDb.insert(Db.RemindersTable.TABLE_NAME,
                                Db.RemindersTable.toContentValues(reminder),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(reminder);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Reminder>> getReminders() {
        return mDb.createQuery(Db.RemindersTable.TABLE_NAME,
                "SELECT * FROM " + Db.RemindersTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Reminder>() {
                    @Override
                    public Reminder call(Cursor cursor) {
                        return Db.RemindersTable.parseCursor(cursor);
                    }
                });
    }

    public Observable<Reminder> addReminders(final Reminder newReminder) {
        return Observable.create(new Observable.OnSubscribe<Reminder>() {
            @Override
            public void call(Subscriber<? super Reminder> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    long result = mDb.insert(Db.RemindersTable.TABLE_NAME,
                            Db.RemindersTable.toContentValues(newReminder),
                            SQLiteDatabase.CONFLICT_FAIL);
                    if (result >= 0) subscriber.onNext(newReminder);
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }
}
