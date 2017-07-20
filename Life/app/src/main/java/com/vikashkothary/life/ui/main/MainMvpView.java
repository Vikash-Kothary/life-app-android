package com.vikashkothary.life.ui.main;

import com.vikashkothary.life.data.model.Ribot;
import com.vikashkothary.life.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
