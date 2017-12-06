package uk.co.ribot.androidboilerplate.ui.detail;

import uk.co.ribot.androidboilerplate.data.model.AppDetailsModel;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public interface DetailMvpView extends MvpView {

    void showDetailView(AppDetailsModel appDetailsModel);

    void showScore(float score);
}
