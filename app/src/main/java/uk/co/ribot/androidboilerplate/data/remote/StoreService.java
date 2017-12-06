package uk.co.ribot.androidboilerplate.data.remote;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.co.ribot.androidboilerplate.data.model.AppDetailsModel;
import uk.co.ribot.androidboilerplate.data.model.IResponse;
import uk.co.ribot.androidboilerplate.data.model.RankListModel;

/**
 * Created by wangweijun on 2017/12/5.
 */

public interface StoreService {

    String ENDPOINT = "http://mapi.letvstore.com/";

    @GET("mapi/edit/recommend")
    Observable<IResponse<RankListModel>> getRankApps(@Query("pagefrom") String pagefrom, @Query("pagesize") String pagesize, @Query("code") String code);

    //    http://base.mapi.letvstore.com/mapi/app/get?packagename=com.happyelements.AndroidAnimal
    @GET("mapi/app/get")
    Observable<IResponse<AppDetailsModel>> getAppDetail(@Query("packagename") String packagename);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static StoreService newStoreService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(StoreService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpUtils.getInstance().getOkHttpClient())
                    .build();
            return retrofit.create(StoreService.class);
        }
    }
}
