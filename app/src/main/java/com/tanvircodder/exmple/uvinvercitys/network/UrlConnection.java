package com.tanvircodder.exmple.uvinvercitys.network;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.tanvircodder.exmple.uvinvercitys.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UrlConnection {
    // todo http://universities.hipolabs.com/search?country=Bangladesh
    private static final String endpoint = "http://universities.hipolabs.com/search";
//    creating some attribut that will work as key and for the value
    private static final String PARAM_KEY_BANGLADESH = "country";
    public static URL buildUrl(String country_name){
        Uri buildUri = Uri.parse(endpoint).buildUpon()
                .appendQueryParameter(PARAM_KEY_BANGLADESH,country_name)
                .build();
        URL newUrl = null;
        try {
            newUrl = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            System.out.println("Can't build the url");
        }
        return newUrl;
    }

    public static String HttpResponse(URL url) throws IOException {
        HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
        try{
            InputStream stream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasnext = scanner.hasNext();
            if (hasnext){
                return scanner.next();
            }else{
                return null;
            }
        }catch (IOException e){
            Log.e(UrlConnection.class.getSimpleName(),"The connection problem"+ urlConnection.getResponseCode());
        }finally {
            urlConnection.disconnect();
        }

        return  null;
    }
}
