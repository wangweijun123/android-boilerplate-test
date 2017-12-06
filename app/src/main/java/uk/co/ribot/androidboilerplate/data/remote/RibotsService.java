package uk.co.ribot.androidboilerplate.data.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uk.co.ribot.androidboilerplate.data.model.Contributor;

public interface RibotsService {

//    String ENDPOINT = "https://api.ribot.io/";
//    String ENDPOINT = "http://mapi.letvstore.com/";
    String ENDPOINT = "https://api.github.com";

    // https://api.github.com/repos/square/retrofit/contributors
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RibotsService newRibotsService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RibotsService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(OkHttpUtils.getInstance().getOkHttpClient())
                    .build();
            return retrofit.create(RibotsService.class);
        }
    }
}
