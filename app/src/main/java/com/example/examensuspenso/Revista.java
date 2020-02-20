package com.example.examensuspenso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {
    private String volume;
    private String number;
    private String portada;
    private String year;

    public Revista(JSONObject a) throws JSONException {
        volume=a.getString("volume").toString();
        number=a.getString("number").toString();
        portada=a.getString("portada").toString();
        year=a.getString("year").toString();
    }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {

        ArrayList<Revista> revistas = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
