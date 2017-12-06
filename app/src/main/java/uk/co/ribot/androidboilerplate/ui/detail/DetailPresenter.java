package uk.co.ribot.androidboilerplate.ui.detail;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.data.model.AppDetailsModel;
import uk.co.ribot.androidboilerplate.data.model.IResponse;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

/**
 * Created by wangweijun1 on 2017/12/6.
 */

public class DetailPresenter extends BasePresenter<DetailMvpView> {

    private final DataManager mDataManager;

    @Inject
    DetailPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void getAppDetail(String packagename) {
        mDataManager.getAppDetail(packagename)
                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io()) // 这里不指定的话，和上游线程保持一致
                .doOnNext(new Consumer<IResponse<AppDetailsModel>>() {
                    @Override
                    public void accept(@NonNull IResponse<AppDetailsModel> appDetailsModelIResponse) throws Exception {
                        mDataManager.getPreferencesHelper().setScore(appDetailsModelIResponse.getEntity().score);

                        Log.i("wang", "accept setScore "+ ", tid:" + Thread.currentThread().getId());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IResponse<AppDetailsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IResponse<AppDetailsModel> appDetailsModelIResponse) {
                        AppDetailsModel appDetailsModel = appDetailsModelIResponse.getEntity();
                        Log.i("wang", "onNext score:" + appDetailsModel.score + ", tid:" + Thread.currentThread().getId());
                        getMvpView().showDetailView(appDetailsModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getScoreFromSP() {
        Observable<Float> observable = Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Float> emitter) throws Exception {
                float score = mDataManager.getPreferencesHelper().getScore();
                Log.i("wang", "subscribe score:" + score + ", tid:" + Thread.currentThread().getId());
                emitter.onNext(score);
                emitter.onComplete();
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Float>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Float s) {
                        Log.i("wang", "onNext s:" + s + ", tid:" + Thread.currentThread().getId());
                        getMvpView().showScore(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void saveScoreToSP(final float score) {
        Observable<Float> observable = Observable.create(new ObservableOnSubscribe<Float>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Float> emitter) throws Exception {
                mDataManager.getPreferencesHelper().setScore(score);
                Log.i("wang", "subscribe score:" + score + ", tid:" + Thread.currentThread().getId());
                emitter.onComplete();
            }
        });
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Float>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Float s) {
                        Log.i("wang", "onNext s:" + s + ", tid:" + Thread.currentThread().getId());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("wang", "onComplete s:" + ", tid:" + Thread.currentThread().getId());
                    }
                });

    }

}
