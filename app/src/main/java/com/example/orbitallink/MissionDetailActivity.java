package com.example.orbitallink;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MissionDetailActivity extends AppCompatActivity {

    private Mision misionActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_detail);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarDetalle);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalles de la Misión");
        }

        misionActual = getIntent().getParcelableExtra("mision_extra");

        if (misionActual == null) {
            finish();
            return;
        }

        ImageView ivPatch = findViewById(R.id.ivDetailPatch);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);
        Button btnWeb = findViewById(R.id.btnWeb);
        Button btnShare = findViewById(R.id.btnShare);
        Button btnSubscribe = findViewById(R.id.btnSubscribe);
        Button btnDownload = findViewById(R.id.btnDownload);

        tvTitle.setText(misionActual.getMision());
        tvDesc.setText(misionActual.getDescripcion());

        Glide.with(this)
                .load(misionActual.getPatch())
                .into(ivPatch);

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(misionActual.getWiki()));
                startActivity(webIntent);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "¡Mira este lanzamiento: " + misionActual.getMision() + " el día " + misionActual.getFecha() + "!");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Compartir misión"));
            }
        });

        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suscribirseLanzamiento();
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarParche();
            }
        });
    }

    private void suscribirseLanzamiento() {
        String url = "http://pabloglezs.es/subscribe.php";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("id_mision", misionActual.getId());
            jsonBody.put("token_usuario", "agente@orbital.link");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MissionDetailActivity.this, "¡Suscripción exitosa!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MissionDetailActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Volley.newRequestQueue(this).add(request);
    }

    private void descargarParche() {
        if (misionActual.getPatch() == null || misionActual.getPatch().isEmpty()) return;

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(misionActual.getPatch()));
        //request.setTitle("Parche " + misionActual.getMision());
        //request.setDescription("Descargando...");
        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, misionActual.getMision()+System.currentTimeMillis());

        DownloadManager manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        if (manager != null) {
            manager.enqueue(request);
            Toast.makeText(this, "Descarga iniciada", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}