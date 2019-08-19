package com.addev.mythread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView = findViewById(R.id.imageView);
        new ImageDownloader().execute();
    }


    Bitmap bitmap;
    private class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //TODO Auto-generated method stub
            URL imageURL = null;
            String image_location = "https://image.tmdb.org/t/p/w185/kSBXou5Ac7vEqKd97wotJumyJvU.jpg";
            try {
                imageURL = new URL(image_location);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) imageURL
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();

                final int THUMBNAIL_SIZE = 200;
                bitmap = BitmapFactory.decodeStream(inputStream);// Convert to
                Float width = new Float(bitmap.getWidth());
                Float height = new Float(bitmap.getHeight());
                Float ratio = width / height; // bitmap
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        (int) (THUMBNAIL_SIZE * ratio), THUMBNAIL_SIZE, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Bitmap img) {
            imageView.setImageBitmap(bitmap);
        }

    }
}
