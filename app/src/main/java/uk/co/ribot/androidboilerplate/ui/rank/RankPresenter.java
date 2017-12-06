package uk.co.ribot.androidboilerplate.ui.rank;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.BaseModel;
import uk.co.ribot.androidboilerplate.data.model.IResponse;
import uk.co.ribot.androidboilerplate.data.model.RankListMiddle;
import uk.co.ribot.androidboilerplate.data.model.RankListModel;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

/**
 * Created by wangweijun on 2017/12/5.
 */

public class RankPresenter extends BasePresenter<RankMvpView> {
    private final DataManager mDataManager;

    @Inject
    public RankPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void getRankApps() {
        mDataManager.getRankApps().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<IResponse<RankListModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull IResponse<RankListModel> ribots) {
                        RankListModel rankListModel = ribots.getEntity();
                       String code =  ribots.getCode();
                        Log.i("wang", "onNext code:"+code);
                        RankListMiddle rankListMiddle = rankListModel.ranklist.get(0);

                        Log.i("wang", "pagesize:"+rankListMiddle.pagesize);

                        List<BaseModel> list =  rankListMiddle.items;
                        for(BaseModel baseModel :  list) {
                            Log.i("wang", "baseModel:"+baseModel);
                        }

                        getMvpView().showDataLoadSuccess(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("wang", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete");
                    }
                });
    }

}
