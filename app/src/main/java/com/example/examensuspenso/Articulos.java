package com.example.examensuspenso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Articulos {
    private String titulo;
    private String Sec;
    private String Num;
    private String PDF;
    private String fecha;

    public Articulos(JSONObject a) throws JSONException {
        titulo =  a.getString("title").toString();
        Sec =  a.getString("section_title").toString();
        Num= a.getString("number").toString();
        PDF =  a.getString("pdf").toString();
        fecha= a.getString("date_published").toString();
    }
    public static ArrayList<Articulos> JsonObjectsBuild(JSONArray datos) throws JSONException {

        ArrayList<Articulos> articulos = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            articulos.add(new Articulos(datos.getJSONObject(i)));
        }
        return articulos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSec() {
        return Sec;
    }

    public void setSec(String sec) {
        Sec = sec;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
