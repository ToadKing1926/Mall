package clouddev.com.czy.network;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import clouddev.com.czy.app.ConfigType;
import clouddev.com.czy.app.appConfig;
import clouddev.com.czy.app.appInit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 29737
 */

public class RestfulCreater
{
    private static final class RetrofitHolder
    {
        private static final String BASE_URL= appInit.getConfiguration(ConfigType.API_HOST);
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                                                                         .baseUrl(BASE_URL)
                                                                        .client(OkHttpHolder.OKHTTP_CLIENT)
                                                                        .addConverterFactory(ScalarsConverterFactory.create())
                                                                        .build();

    }
    /**
     * 参数容器
     */
    private static final class ParamsHolder
    {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class OkHttpHolder
    {
        private static final int TIME_OUT=60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static final ArrayList<Interceptor> INTERCEPTORS = appInit.getConfiguration(ConfigType.INTERCEPTER);

        private static OkHttpClient.Builder addInterceptor()
        {
            if(INTERCEPTORS != null && !INTERCEPTORS.isEmpty())
            {
                for(Interceptor interceptor: INTERCEPTORS )
                {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OKHTTP_CLIENT=addInterceptor().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                                                                      .build();

    }

    private static final class RestfulServiceHolder
    {
        private static final RestfulService RESTFUL_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestfulService.class);

    }

    public static RestfulService getRestfulService()
    {
        return RestfulServiceHolder.RESTFUL_SERVICE;
    }


}
