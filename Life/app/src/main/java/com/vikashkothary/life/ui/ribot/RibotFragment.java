package com.vikashkothary.life.ui.ribot;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vikashkothary.life.R;
import com.vikashkothary.life.data.SyncService;
import com.vikashkothary.life.data.model.Ribot;
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
public class RibotFragment extends BaseFragment implements RibotMvpView{

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.vikashkothary.life.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject RibotPresenter mRibotPresenter;
    @Inject RibotsAdapter mRibotsAdapter;

    @BindView(R.id.recycler_view_ribot) RecyclerView mRibotRecycler;
    @BindView(R.id.swipe_to_refresh) SwipeRefreshLayout mSwipeToRefresh;
    @BindView(R.id.progress) ProgressBar mProgress;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static RibotFragment newInstance() {
        return newInstance(true);
    }

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static RibotFragment newInstance(boolean triggerDataSyncOnCreate) {
        RibotFragment fragment = new RibotFragment();
        Bundle args = new Bundle();
        args.putBoolean(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

        if (getArguments().getBoolean(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            getActivity().startService(SyncService.getStartIntent(getActivity()));
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_ribot, container, false);
        ButterKnife.bind(this, fragmentView);

        getActivity().setTitle("Ribots");

        mRibotRecycler.setHasFixedSize(true);
        mRibotRecycler.setAdapter(mRibotsAdapter);
        mRibotRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRibotPresenter.attachView(this);
        mSwipeToRefresh.setColorSchemeResources(R.color.primary);
        mSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRibotPresenter.loadRibots();
            }
        });

        return fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mRibotPresenter.loadRibots();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRibotPresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showRibots(List<Ribot> ribots) {
        mRibotsAdapter.setRibots(ribots);
        mRibotsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRibotProgress(boolean show) {
        mSwipeToRefresh.setRefreshing(show);
        if (show && mRibotsAdapter.getItemCount() == 0) {
            mProgress.setVisibility(View.VISIBLE);
        } else {
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(getActivity(), getString(R.string.error_loading_ribots))
                .show();
    }

    @Override
    public void showRibotsEmpty() {
        mRibotsAdapter.setRibots(Collections.<Ribot>emptyList());
        mRibotsAdapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }

}
