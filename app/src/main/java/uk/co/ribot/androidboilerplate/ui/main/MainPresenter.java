package uk.co.ribot.androidboilerplate.ui.main;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.Contributor;
import uk.co.ribot.androidboilerplate.injection.ConfigPersistent;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;
import uk.co.ribot.androidboilerplate.util.RxUtil;

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


    public void loadContributors() {
        checkViewAttached();
        RxUtil.dispose(mDisposable);
        Log.i("wang", "loadContributors mDataManager:"+mDataManager);
        mDataManager.contributors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<Contributor> contributors) {
                        Log.i("wang", "onNext ...");
                        for (Contributor con:contributors) {
                            Log.i("wang", "contributor:"+con);
                        }
                        getMvpView().showDataLoadSuccessTip(contributors);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().hideErrorUI();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
