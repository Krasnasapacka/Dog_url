package com.example.liudas.imageloading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();

    ImageView [] ImageArray = new ImageView[10]; //bus atvaizduojamos pirmos 10 nuotrauku

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int counter = 0;

        try {
            JSONObject obj = new JSONObject(loadJSON());
            JSONArray JArray = obj.getJSONArray("urls");
            for (int i = 0; i < JArray.length(); i++) {
                String link =JArray.getString(i);
                counter=counter+1;
                list.add(i,link); //visos nuorodos sudedamos i sarasa
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String [] urls = list.toArray(new String[list.size()]); // nuorodu masyvui priskiriamas listas
        Log.d("Details-->",""+urls[5]);
        //ImageArray priskiriami imageView
        ImageArray[0]= (ImageView) findViewById(R.id.imageView1);
        ImageArray[1]= (ImageView) findViewById(R.id.imageView2);
        ImageArray[2]= (ImageView) findViewById(R.id.imageView3);
        ImageArray[3]= (ImageView) findViewById(R.id.imageView4);
        ImageArray[4]= (ImageView) findViewById(R.id.imageView5);
        ImageArray[5]= (ImageView) findViewById(R.id.imageView6);
        ImageArray[6]= (ImageView) findViewById(R.id.imageView7);
        ImageArray[7]= (ImageView) findViewById(R.id.imageView8);
        ImageArray[8]= (ImageView) findViewById(R.id.imageView9);
        ImageArray[9]= (ImageView) findViewById(R.id.imageView10);

        for (int j=0; j<10; j++)
        {
            loadImage(urls[j],j); //perduodame j, kad žinotume kelintą ImageArray elementa naudoti
        }

    }
    //paveikslo parodymas ekrane
    private void loadImage(String url, int j) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ImageArray[j],new com.squareup.picasso.Callback(){

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
    // JSON failo nuskaitymas is Asset aplanko
    public String loadJSON() {
        String json = null;
        try {
            InputStream is = getAssets().open("dog_urls.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
