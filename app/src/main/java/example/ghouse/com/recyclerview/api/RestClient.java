package example.ghouse.com.recyclerview.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Ghouse on 11/8/16.
 *
 */

public class RestClient {

//    API Example - "http://api.themoviedb.org/3/movie/top_rated?api_key=7e8f60e325cd06e164799af1e317d7a7"
    public static final String BASE_URL = "http://api.themoviedb.org/3/";

//    public static Retrofit getInitializedRestAdapter(final String accessToken) {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                                                .connectTimeout(10, TimeUnit.SECONDS)
//                                                .writeTimeout(10, TimeUnit.SECONDS)
//                                                .readTimeout(30, TimeUnit.SECONDS);
//        // add your other interceptors
//        Interceptor interceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) {
//                Request original = chain.request();
//
//                Request.Builder requestBuilder = original.newBuilder();
//
//                if(!TextUtils.isEmpty(accessToken)) {
//                    final String bearer = "Bearer " + accessToken;
//                    requestBuilder.header(Constants.API_HEADER_PARAM_AUTHORIZATION, bearer);
//                }
//
//                requestBuilder.header("Accept", "application/json");
//                requestBuilder.header("Content-Type", "application/json");
//                requestBuilder.method(original.method(), original.body());
//                Request request = requestBuilder.build();
//                try {
//                    return chain.proceed(request);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        };
//
//        httpClient.interceptors().add(interceptor);
//
//        // add logging as last interceptor
//        httpClient.interceptors().add(logging);  // <-- this is the important line!
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(Constants.DOMAIN)
//                .client(httpClient.build())
//                .addConverterFactory(new GsonStringConverterFactory())
//                .addConverterFactory(GsonConverterFactory.create());
//        return builder.build();
//    }

    public static Retrofit getInitializedRestAdapterWithOutAuthorizationHeader() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                                                .connectTimeout(10, TimeUnit.SECONDS)
                                                .writeTimeout(10, TimeUnit.SECONDS)
                                                .readTimeout(30, TimeUnit.SECONDS);
        // add your other interceptors
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                try {
                    return chain.proceed(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        httpClient.interceptors().add(interceptor);

        // add logging as last interceptor
        httpClient.interceptors().add(logging);  // <-- this is the important line!
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(new GsonStringConverterFactory())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();

    }
}
