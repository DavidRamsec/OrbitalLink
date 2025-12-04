package com.example.orbitallink;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiRetrofit {

    static List<Mision> listaMisiones = new ArrayList<>();
    private void descargarMisionesRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pabloglezs.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServicio api = retrofit.create(ApiServicio.class);

        Call<List<Mision>> llamada = api.obtenerMisiones();
        llamada.enqueue(new Callback<List<Mision>>() {
            @Override
            public void onResponse(Call<List<Mision>> call, Response<List<Mision>> response) {
                listaMisiones = response.body();
            }

            @Override
            public void onFailure(Call<List<Mision>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
