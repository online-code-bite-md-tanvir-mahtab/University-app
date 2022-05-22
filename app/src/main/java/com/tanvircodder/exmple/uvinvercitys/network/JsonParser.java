package com.tanvircodder.exmple.uvinvercitys.network;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.tanvircodder.exmple.uvinvercitys.DefaultFragment;
import com.tanvircodder.exmple.uvinvercitys.MainActivity;
import com.tanvircodder.exmple.uvinvercitys.databaes.DatabaseClient;
import com.tanvircodder.exmple.uvinvercitys.model.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private static List<Util> listOfData = null;
    public static List<Util> UrlJsonForParsing(Context context, String response) throws JSONException {
        JSONArray responseArray = new JSONArray(response);
        listOfData = new ArrayList<>();
        int totalResult = responseArray.length();
        for (int i =0;i<responseArray.length();i++){
            JSONObject jsonObject = responseArray.getJSONObject(i);
            String mName = jsonObject.getString("name");
            JSONArray array = jsonObject.getJSONArray("domains");
            JSONArray array2 = jsonObject.getJSONArray("web_pages");
            for (int j =0;j<array.length();j++){
                String domain = array.getString(j);
                for (int z=0;z<array2.length();z++){
                    String url = array2.getString(z);
                    class SaveName extends AsyncTask<Void,Void,Void> {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            Util util = new Util();
                            util.setmName(mName);
                            util.setmDomains(domain);
                            util.setmUrl(url);
                            listOfData.add(util);
                            DatabaseClient.getmInstance(context).getAppDatabase()
                                    .versityDao()
                                    .insert(util);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            Toast.makeText(context,"Saved",Toast.LENGTH_LONG).show();
                        }
                    }
                    SaveName saveName = new SaveName();
                    saveName.execute();
                }

            }
        }
        return listOfData;
    }
}
