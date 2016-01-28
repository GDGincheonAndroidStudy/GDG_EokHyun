package com.ykyahwa.bookbestseller.network;

import com.ykyahwa.bookbestseller.data.BookListData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by eokhyunlee on 2016. 1. 22..
 */
public class NetworkRetrofit {
    private final String INTERPARK_API_URL = "http://book.interpark.com";

    public void request(Callback<BookListData> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(INTERPARK_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterpartService service = retrofit.create(InterpartService.class);
        Call<BookListData> call = service.getBestSeller();
        call.enqueue(callback);
    }

    public interface InterpartService {


        String BESTSELLER_QUERY = "/api/bestSeller.api?key=interpark&categoryId=100&output=json";

        @GET(BESTSELLER_QUERY)
        Call<BookListData> getBestSeller();
    }
}
