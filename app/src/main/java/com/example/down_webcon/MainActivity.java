package com.example.down_webcon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class downloadTask extends AsyncTask<String,Void, String>
    {

        @Override
        protected String doInBackground(String... url) {
            URL url1;
            String res = "";
            HttpURLConnection httpURLConnection = null;
            try
            {
                url1 =  new URL(url[0]);
                httpURLConnection  = (HttpURLConnection)url1.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);

                int data  = reader.read();
                while (data != -1)
                {
                    char rData = (char) data;
                    res+=rData;
                    data = reader.read();

                }
                return res;
            }catch (Exception e)
            {
                e.printStackTrace();
                return "Failed";
            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String res = null;
        downloadTask task  = new downloadTask();
       try{
             res = task.execute("https://anilist.co/home").get();
       }catch (Exception e)
       {
           Log.i("Exception",e.getMessage());
           e.getStackTrace();
       }

       Log.i("URL",res);
    }
}