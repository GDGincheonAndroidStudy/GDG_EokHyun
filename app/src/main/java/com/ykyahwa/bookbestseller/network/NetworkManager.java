package com.ykyahwa.bookbestseller.network;

import android.util.Log;

import com.google.gson.Gson;
import com.ykyahwa.bookbestseller.data.BookListData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ehlee on 2016-01-13.
 */
public class NetworkManager extends Thread {

    private final String INTERPARK_API_URL = "http://book.interpark.com/api/bestSeller.api?key=interpark&categoryId=100&output=json";
    private NetworkListner listener;

    public NetworkManager(NetworkListner listener) {
        this.listener = listener;
    }

    private void requestBestSeller() throws IOException {
        URL url = new URL(INTERPARK_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        String responseMessage = conn.getResponseMessage();

        Log.i("BestSeller", "responseCode = " + responseCode);
        Log.i("BestSeller", "responseMessage = " + responseMessage);
        byte[] responseByteArray = getResponseByteArray(conn.getInputStream());

        String responseJson = new String(responseByteArray, 0, responseByteArray.length,"UTF-8");
        Log.i("BestSeller", "responseJson = " + responseJson);

        parseData(responseJson);

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

    private void parseData(String responseJson) {
        Gson gson = new Gson();
        BookListData data = gson.fromJson(responseJson,
                BookListData.class);

        listener.onBestSelletResponseData(data);
    }

    @Override
    public void run() {
        try {
            requestBestSeller();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

