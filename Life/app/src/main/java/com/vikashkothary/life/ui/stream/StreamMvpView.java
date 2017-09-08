package com.vikashkothary.life.ui.stream;

import com.vikashkothary.life.data.model.Ribot;
import com.vikashkothary.life.ui.base.MvpView;

import java.util.List;

public interface StreamMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
