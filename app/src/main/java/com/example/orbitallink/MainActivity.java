package com.example.orbitallink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    List<Mision> listaDeElementos;
    RecyclerView listaMisiones;
    MisionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.barraAccion);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Orbital Link");
        }
        cargarComponentes();
    }

    public void cargarComponentes() {
        listaMisiones = findViewById(R.id.lista_misiones);
        listaMisiones.setLayoutManager(new LinearLayoutManager(this));
        listaDeElementos = new ArrayList<>();

        adapter = new MisionAdapter(listaDeElementos, new MisionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mision item) {
                clicEnElemento(item);
            }
        });

        listaMisiones.setAdapter(adapter);
        cargarDatos();
    }

    private void clicEnElemento(Mision item) {
        String mensaje = "Seleccionaste: " + item.getMision();
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MissionDetailActivity.class);
        intent.putExtra("mision_extra", item);
        startActivity(intent);
    }

    private void cargarDatos() {
        // Usamos el Singleton de Retrofit que creamos
        MiRetrofit.getInstance().getMyApi().obtenerMisiones().enqueue(new Callback<List<Mision>>() {
            @Override
            public void onResponse(Call<List<Mision>> call, Response<List<Mision>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Limpiamos la lista vieja y añadimos las nuevas misiones
                    listaDeElementos.clear();
                    listaDeElementos.addAll(response.body());
                    // Avisamos al adaptador de que hay datos nuevos
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Mision>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull android.view.MenuItem item) {
        if (item.getItemId() == R.id.recargar) {
            Toast.makeText(this, "Actualizando misiones", Toast.LENGTH_SHORT).show();
            cargarDatos();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}