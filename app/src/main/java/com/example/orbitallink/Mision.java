package com.example.orbitallink;

import com.google.gson.annotations.SerializedName;

public class Mision {
    @SerializedName("id")
    private int id;
    @SerializedName("mision")
    private String mision;
    @SerializedName("agencia")
    private String agencia;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("descripcion")
    private String descripcion;
    @SerializedName("rocket_img")
    private int rocket_img;
    @SerializedName("patch_img")
    private int patch_img;
    @SerializedName("wiki_url")
    private String wiki_url;

    public Mision(int rocket_img, String mision, String agencia) {
        this.mision = mision;
        this.agencia = agencia;
        this.rocket_img = rocket_img;
    }

    public int getId() {
        return id;
    }

    public String getMision() {
        return mision;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getRocket() {
        return rocket_img;
    }

    public int getPatch() {
        return patch_img;
    }

    public String getWiki() {
        return wiki_url;
    }
}
