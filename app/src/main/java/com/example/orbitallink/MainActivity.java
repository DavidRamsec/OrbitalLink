package com.example.orbitallink;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        cargarComponentes();
    }
    public void cargarComponentes(){
        listaMisiones = findViewById(R.id.lista_misiones);
        listaMisiones.setLayoutManager(new LinearLayoutManager(this));
        cargarDatos();
        adapter = new MisionAdapter(listaDeElementos, new MisionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Mision item) {
                clicEnElemento(item);
            }
        });

        listaMisiones.setAdapter(adapter);
    }
    private void clicEnElemento(Mision item) {
        String mensaje = "Seleccionaste: " + item.getMision();
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void cargarDatos(){
        MiRetrofit retrofit = new MiRetrofit();
    }
    private void cargarDatosP() {
        listaDeElementos = new ArrayList<>();

        // Nota: Asegúrate de tener imágenes en res/drawable o usa ic_launcher_background como placeholder
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Jelly Bean", "Version: 4.1 - 4.3.1, Api Name: 16-18"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "KitKat", "Version: 4.4 - 4.4.4, Api Name: 19-20"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Lollipop", "Version: 5.0 - 5.1.1, Api Name: 21-22"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Marshmallow", "Version: 6.0 - 6.0.1, Api Name: 23"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Nougat", "Version: 7.0 - 7.1.2, Api Name: 24-25"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "KitKat", "Version: 4.4 - 4.4.4, Api Name: 19-20"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Lollipop", "Version: 5.0 - 5.1.1, Api Name: 21-22"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Marshmallow", "Version: 6.0 - 6.0.1, Api Name: 23"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "KitKat", "Version: 4.4 - 4.4.4, Api Name: 19-20"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Lollipop", "Version: 5.0 - 5.1.1, Api Name: 21-22"));
        listaDeElementos.add(new Mision(R.drawable.ic_launcher_foreground, "Marshmallow", "Version: 6.0 - 6.0.1, Api Name: 23"));

    }
}