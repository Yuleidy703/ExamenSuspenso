package com.example.examensuspenso;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorArticulo extends ArrayAdapter<Articulos> {
    public AdaptadorArticulo(Context context, ArrayList<Articulos> datos) {
        super(context, R.layout.ly_itemarticulos, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.ly_itemarticulos, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.lblTitulo);
        lblTitulo.setText(getItem(position).getTitulo());

        TextView lblSeccion = (TextView)item.findViewById(R.id.lblSeccion);
        lblSeccion.setText(getItem(position).getSec());

        TextView lblFechaPub= (TextView)item.findViewById(R.id.lblFechaPub);
        lblFechaPub.setText(getItem(position).getFecha());

        TextView lblPDF = (TextView)item.findViewById(R.id.lblpdf);
        lblPDF.setText(getItem(position).getPDF());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgPortada);
        Glide.with(this.getContext())
                .load(getItem(position).getPDF())
                .error(R.drawable.pdflogo)
                .into(imageView);

        imageView.setTag(getItem(position).getPDF());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = v.getTag().toString();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription("PDF Paper");
                request.setTitle("Pdf Artcilee");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filedownload.pdf");

                DownloadManager manager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                try {
                    manager.enqueue(request);

                } catch (Exception e) {

                    Toast.makeText(getContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();

                }

            }
        });
        return(item);


    }
}
