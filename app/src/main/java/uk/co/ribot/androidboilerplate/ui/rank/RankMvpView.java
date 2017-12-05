package uk.co.ribot.androidboilerplate.ui.rank;

import java.util.List;

import uk.co.ribot.androidboilerplate.data.model.BaseModel;
import uk.co.ribot.androidboilerplate.ui.base.MvpView;

/**
 * Created by wangweijun on 2017/12/5.
 */

public interface RankMvpView extends MvpView {

    void showDataLoadSuccess(List<BaseModel> list);

    void showDataLoadError();
}
