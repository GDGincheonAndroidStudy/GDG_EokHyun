package com.ykyahwa.bookbestseller;

import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import com.ykyahwa.bookbestseller.data.BookListData;
import com.ykyahwa.bookbestseller.network.NetworkRetrofit;

import org.junit.Before;

import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

/**
 * Created by eokhyunlee on 2016. 1. 21..
 */
@SmallTest
public class RetrofitTest implements Callback<BookListData> {
    private BookListData data;
    @Before
    public void request(){
        new NetworkRetrofit().request(this);


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void test() {
        assertTrue("미움받을 용기".equals(data.getItem().get(0).getTitle()));
    }



    @Override
    public void onResponse(Response<BookListData> response) {
        Log.d("TEST", "onResponse");
        this.data = response.body();
    }

    @Override
    public void onFailure(Throwable t) {
        Log.d("TEST", "onFailure");

    }
}
