package com.example.orbitallink;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mision implements Parcelable{
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
    private String rocket_img;
    @SerializedName("patch_img")
    private String patch_img;
    @SerializedName("wiki_url")
    private String wiki_url;
    public Mision() {}
    protected Mision(Parcel in) {
        id = in.readInt();
        mision = in.readString();
        agencia = in.readString();
        fecha = in.readString();
        descripcion = in.readString();
        rocket_img = in.readString();
        patch_img = in.readString();
        wiki_url = in.readString();
    }

    public static final Creator<Mision> CREATOR = new Creator<Mision>() {
        @Override
        public Mision createFromParcel(Parcel in) {
            return new Mision(in);
        }

        @Override
        public Mision[] newArray(int size) {
            return new Mision[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(mision);
        dest.writeString(agencia);
        dest.writeString(fecha);
        dest.writeString(descripcion);
        dest.writeString(rocket_img);
        dest.writeString(patch_img);
        dest.writeString(wiki_url);
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

    public String getRocket() {
        return rocket_img;
    }

    public String getPatch() {
        return patch_img;
    }

    public String getWiki() {
        return wiki_url;
    }
}
