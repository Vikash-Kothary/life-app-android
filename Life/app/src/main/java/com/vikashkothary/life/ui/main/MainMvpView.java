package com.vikashkothary.life.ui.main;

import java.util.List;

import com.vikashkothary.life.data.model.Ribot;
import com.vikashkothary.life.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
