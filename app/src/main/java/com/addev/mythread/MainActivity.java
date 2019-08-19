package com.addev.mythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnThread;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThread = findViewById(R.id.button);
        tv=findViewById(R.id.tv);
        btnThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new Thread(new Runnable() {
            public void run() {
             //final String txt = loadStringFromNetwork("https://api.themoviedb.org/3/movie/%7Bmovie_id%7D?api_key=%3C%3Capi_key%3E%3E&language=en-US");
              final String txt = loadStringFromNetwork("https://api.themoviedb.org/3/movie/550?api_key="+BuildConfig.ApiKey+"&language=en-US");
                Message msg = Message.obtain();
                msg.obj = txt;
                msg.setTarget(handler);
                msg.sendToTarget();
            }
        }).start();
    }

    private Handler handler =  new Handler(){
        public void handleMessage (Message msg){
            String message = (String) msg.obj;
            tv.setText(message);
        }
    };

    String loadStringFromNetwork(final String url){
        String result="";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
           result = String.valueOf(connection.getResponseCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
