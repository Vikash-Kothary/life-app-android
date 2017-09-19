package com.vikashkothary.life.ui.stream;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vikashkothary.life.R;
import com.vikashkothary.life.data.model.Reminder;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.base.BaseFragment;
import com.vikashkothary.life.util.DialogFactory;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamFragment extends BaseFragment implements StreamMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.vikashkothary.life.ui.ribot.StreamFragment.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject
    StreamPresenter mStreamPresenter;
    @Inject
    ReminderAdapter mReminderAdapter;

    @BindView(R.id.recycler_view_reminder)
    RecyclerView mStreamRecycler;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout mSwipeToRefresh;

    public static StreamFragment newInstance() {
        StreamFragment fragment = new StreamFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

//        if (getArguments().getBoolean(EXTRA_TRIGGER_SYNC_FLAG, false)) {
//            getActivity().startService(SyncService.getStartIntent(getActivity()));
//        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_stream, container, false);
        ButterKnife.bind(this, fragmentView);

        getActivity().setTitle("Reminders");

        mStreamRecycler.setHasFixedSize(true);
        mStreamRecycler.setAdapter(mReminderAdapter);
        mStreamRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mStreamPresenter.attachView(this);
        mSwipeToRefresh.setColorSchemeResources(R.color.primary);
        mSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mStreamPresenter.mDataManager.setReminders();
                mStreamPresenter.loadReminders();
            }
        });


        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mStreamPresenter.loadReminders();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mStreamPresenter.detachView();
        mStreamPresenter.mDataManager.setReminders();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showReminders(List<Reminder> reminders) {
        mStreamPresenter.mDataManager.mReminders = reminders;
        mReminderAdapter.setReminders(reminders);
        mReminderAdapter.notifyDataSetChanged();
        mSwipeToRefresh.setRefreshing(false);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(getActivity(), getString(R.string.error_loading_reminders))
                .show();
        mSwipeToRefresh.setRefreshing(false);
    }

    @Override
    public void showRemindersEmpty() {
        mReminderAdapter.setReminders(Collections.<Reminder>emptyList());
        mReminderAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), R.string.empty_reminders, Toast.LENGTH_LONG).show();
        mSwipeToRefresh.setRefreshing(false);
    }

}
