package com.example.orbitallink;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiRetrofit {
    private static MiRetrofit instance = null;
    private ApiServicio myApi;

    private MiRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pabloglezs.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiServicio.class);
    }

    public static synchronized MiRetrofit getInstance() {
        if (instance == null) {
            instance = new MiRetrofit();
        }
        return instance;
    }

    public ApiServicio getMyApi() {
        return myApi;
    }
}
