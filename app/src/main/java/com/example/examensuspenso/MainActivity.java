package com.example.examensuspenso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        String url="http://revistas.uteq.edu.ec/ws/getrevistas.php";
        WebService ws = new WebService(url,datos,MainActivity.this,MainActivity.this);
        ws.execute("");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObj = new JSONObject(result);
        JSONArray revistas = jsonObj.getJSONArray("issues");

        final ArrayList<Revista> revista = new ArrayList<>();

        for(int i=0; i<revistas.length(); i++){
            revista.add(new Revista(revistas.getJSONObject(i)));
        }

        AdaptadorRevista adaptadorvolumenes = new AdaptadorRevista(this,revista);
        ListView lstrevista =(ListView)findViewById(R.id.lstrevista);
        lstrevista.setAdapter(adaptadorvolumenes);

        lstrevista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ArticulosActivity.class);

                Bundle b = new Bundle();
                b.putString("volumen",((Revista)parent.getItemAtPosition(position)).getVolume());

                b.putString("numero",((Revista)parent.getItemAtPosition(position)).getNumber());
                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }


}
