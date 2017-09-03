package com.vikashkothary.life;

import com.vikashkothary.life.data.DataManager;
import com.vikashkothary.life.data.model.Ribot;
import com.vikashkothary.life.test.common.TestDataFactory;
import com.vikashkothary.life.ui.ribot.RibotMvpView;
import com.vikashkothary.life.ui.ribot.RibotPresenter;
import com.vikashkothary.life.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RibotPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();
    @Mock
    RibotMvpView mMockRibotMvpView;
    @Mock
    DataManager mMockDataManager;
    private RibotPresenter mRibotPresenter;

    @Before
    public void setUp() {
        mRibotPresenter = new RibotPresenter(mMockDataManager);
        mRibotPresenter.attachView(mMockRibotMvpView);
    }

    @After
    public void tearDown() {
        mRibotPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Ribot> ribots = TestDataFactory.makeListRibots(10);
        when(mMockDataManager.getRibots())
                .thenReturn(Observable.just(ribots));

        mRibotPresenter.loadRibots();
        verify(mMockRibotMvpView).showRibots(ribots);
        verify(mMockRibotMvpView, never()).showRibotsEmpty();
        verify(mMockRibotMvpView, never()).showError();
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
        when(mMockDataManager.getRibots())
                .thenReturn(Observable.just(Collections.<Ribot>emptyList()));

        mRibotPresenter.loadRibots();
        verify(mMockRibotMvpView).showRibotsEmpty();
        verify(mMockRibotMvpView, never()).showRibots(ArgumentMatchers.<Ribot>anyList());
        verify(mMockRibotMvpView, never()).showError();
    }

    @Test
    public void loadRibotsFails() {
        when(mMockDataManager.getRibots())
                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));

        mRibotPresenter.loadRibots();
        verify(mMockRibotMvpView).showError();
        verify(mMockRibotMvpView, never()).showRibotsEmpty();
        verify(mMockRibotMvpView, never()).showRibots(ArgumentMatchers.<Ribot>anyList());
    }

}
