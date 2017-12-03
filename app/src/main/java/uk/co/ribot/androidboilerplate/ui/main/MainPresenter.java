package uk.co.ribot.androidboilerplate.ui.main;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.MyResp;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

// 在当前组件中单例
@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;// single
    private Disposable mDisposable;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

    public void getRankApps() {
        mDataManager.getRankApps().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MyResp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("wang", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NonNull MyResp ribots) {
                        Log.i("wang", "onNext ribots.status: "+ribots.status);
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
