package com.ykyahwa.bookbestseller.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.ykyahwa.bookbestseller.data.BookListData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eokhyunlee on 2016. 1. 16..
 */
public class NetworkAsyncTask extends AsyncTask<Void, Void, String> {

    private final String INTERPARK_API_URL = "http://book.interpark.com/api/bestSeller.api?key=interpark&categoryId=100&output=json";
    private NetworkListner listener;

    public NetworkAsyncTask(NetworkListner listener) {
        this.listener = listener;
    }


    @Override
    protected String doInBackground(Void... params) {

        String responseJson = null;
        try {
            URL url = new URL(INTERPARK_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();

            Log.i("BestSeller", "responseCode = " + responseCode);
            Log.i("BestSeller", "responseMessage = " + responseMessage);
            byte[] responseByteArray = getResponseByteArray(conn.getInputStream());

            responseJson = new String(responseByteArray, 0, responseByteArray.length,"UTF-8");
            Log.i("BestSeller", "responseJson = " + responseJson);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    @Override
    protected void onPostExecute(String jsonData) {
        Gson gson = new Gson();
        BookListData data = gson.fromJson(jsonData,
                BookListData.class);

        listener.onBestSelletResponseData(data);
    }

    private byte[] getResponseByteArray(InputStream is) throws IOException{
        int nReadCount = 0;
        byte[] buffer = new byte[1024 *4];

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((nReadCount = is.read(buffer)) != -1) {
                bos.write(buffer, 0, nReadCount);
            }
            bos.flush();
        } catch (IOException e) {
            throw e;
        } finally{
            bos.close();
            is.close();
        }

        return bos.toByteArray();
    }
}
