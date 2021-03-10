package com.laioffer.tinnews.network;

import android.content.Context;

import com.ashokvarma.gander.GanderInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String API_KEY = "ccda32d870c248569db0f564a5ec7119";
    //The base URL. Sometimes you can have different base URLs, for production, develop, and staging environment.
    private static final String BASE_URL = "https://newsapi.org/v2/";

    public static Retrofit newInstance(Context context) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new GanderInterceptor(context).showNotification(true))
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    //A header interceptor. You can attach custom or standard header information to all requests.
    private static class HeaderInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API_KEY)
                    .build();
            return chain.proceed(request);
        }
    }
    //Gson adapter. This is to tell how a JSON response can be deserialized into model classes.
}
