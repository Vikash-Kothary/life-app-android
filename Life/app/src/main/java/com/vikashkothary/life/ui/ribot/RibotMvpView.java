package com.vikashkothary.life.ui.ribot;

import java.util.List;

import com.vikashkothary.life.data.model.Ribot;
import com.vikashkothary.life.ui.base.MvpView;

public interface RibotMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotProgress(boolean show);

    void showRibotsEmpty();

    void showError();

}
