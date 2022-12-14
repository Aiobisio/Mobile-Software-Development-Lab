package com.jnu.student.Data;

import android.util.Log;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpDataLoader {
    @NonNull
    public String getHttpData(String path){
        try{
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStreamReader inputStreamReader = new InputStreamReader(conn.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String tempLine = null;
                StringBuffer resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
                Log.i("test data",resultBuffer.toString());
                return resultBuffer.toString();
            }
        }catch(Exception exception)
        {
            Log.e("error",exception.getMessage());
        }
        return "";
    }

    @NonNull
    public List<Location> ParseJsonData(String JsonText)
    {
        List<Location> locations=new ArrayList<>();
        try {
            JSONObject root = new JSONObject(JsonText);

            JSONArray bookshelves = root.getJSONArray("bookshelves");
            for(int i=0;i<bookshelves.length();++i){
                JSONObject bookshelf=bookshelves.getJSONObject(i);

                Location Location=new Location();
                Location.setName(bookshelf.getString("name"));
                Location.setLatitude(bookshelf.getDouble("latitude"));
                Location.setLongitude(bookshelf.getDouble("longitude"));
                Location.setMemo(bookshelf.getString("memo"));

                locations.add(Location);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return locations;
    }
}

