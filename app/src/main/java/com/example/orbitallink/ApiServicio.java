package com.example.orbitallink;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiServicio {
    @GET("lanzamientos.json")
    Call<List<Mision>> obtenerMisiones();
}
