package com.example.examensuspenso;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.Feature;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class ArticulosActivity extends AppCompatActivity implements Asynchtask {

    public Vision vision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulos);
        Vision.Builder visionBuilder = new Vision.Builder(new NetHttpTransport(),
                new AndroidJsonFactory(),  null);
        visionBuilder.setVisionRequestInitializer(new VisionRequestInitializer(""));
        vision = visionBuilder.build();


        Bundle bundle = this.getIntent().getExtras();
        String vol= bundle.getString("volumen");
        String num= bundle.getString("numero");
        String url ="http://revistas.uteq.edu.ec/ws/getarticles.php?volumen=" +vol+"&num=" +num;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,datos,ArticulosActivity.this,ArticulosActivity.this);
        ws.execute("");
    }
    public boolean permisos(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Error de Permiso","You have permission");
                return true;
            } else {

                Log.e("Error de Permiso","You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error","You already have the permission");
            return true;
        }
    }


    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObj = new JSONObject(result);
        JSONArray articulos = jsonObj.getJSONArray("articles");

        final ArrayList<Articulos> articulo = new ArrayList<>();

        for(int i=0; i<articulos.length(); i++){
            articulo.add(new Articulos(articulos.getJSONObject(i)));
        }

        AdaptadorArticulo adaptadorarticulos = new AdaptadorArticulo(this,articulo);
        ListView lstarticulos =(ListView)findViewById(R.id.lstarticulo);
        lstarticulos.setAdapter(adaptadorarticulos);
        permisos();
    }

    public void  btnclickWebDetections(String nombreArticulo, View view){
        AsyncTask.execute (new Runnable() {
            @Override
            public void run() {


                //1 paso
                //Image inputImage = new Image();
                //inputImage.encodeContent(imageInByte);
                //2 paso
                Feature desiredFeature = new Feature();
                desiredFeature.setType("WEB_DETECTION");

                //paso 3
                //AnnotateImageRequest request = new AnnotateImageRequest();
                //request.setImage(inputImage);
                //request.setFeatures(Arrays.asList(desiredFeature));
                //BatchAnnotateImagesRequest batchRequest =  new BatchAnnotateImagesRequest();
                //batchRequest.setRequests(Arrays.asList(request));

                // 4 paso
                //try {
                  //  Vision.Images.Annotate annotateRequest =
                    //        vision.images().annotate(batchRequest);
                    //5 paso
                    //annotateRequest.setDisableGZipContent(true);
                    //BatchAnnotateImagesResponse batchResponse  = annotateRequest.execute();
                    // paso 6
                    //StringBuilder message = new StringBuilder("I found these things:\n\n");
                    //WebDetection annotation = batchResponse.getResponses().get(0).getWebDetection();
                    //WebDetection lsts=batchResponse.getResponses().get(0).getWebDetection();
                    //List<WebEntity> lst = annotation.getWebEntities();
                    //List<String> dt= new ArrayList<>();
                    //for (WebEntity entity : lst) {
                      //  dt.add( entity.getDescription());
                    //}
                    //String mensaje="";
                    //mensaje += dt.get(0).toString();

                    //final String result =mensaje.toString();
                    // paso 7
                    //runOnUiThread(new Runnable() {
                        //@Override
                      //  public void run() {
                        //    TextView imageDetail = (TextView)findViewById(R.id.textView2);
                          //  imageDetail.setText(result);
                        //}
                    //});



                //} catch (IOException e) {
                  //  e.printStackTrace();
                //}


            }
        });
    }
}
