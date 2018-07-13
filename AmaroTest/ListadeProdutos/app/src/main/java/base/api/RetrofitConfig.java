package base.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kleyton on 12/07/18.
 */

public class RetrofitConfig {

    public static final String URL_BASE = "http://www.mocky.io/v2/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static Retrofit getInstance() {
        return retrofit;
    }

    public static ServiceApi getService(){

        retrofit = getInstance();
        return retrofit.create(ServiceApi.class);
    }

}
