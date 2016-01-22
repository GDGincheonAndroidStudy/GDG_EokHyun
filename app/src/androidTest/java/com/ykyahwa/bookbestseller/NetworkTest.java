package com.ykyahwa.bookbestseller;

import android.test.suitebuilder.annotation.SmallTest;

import com.ykyahwa.bookbestseller.data.BookListData;
import com.ykyahwa.bookbestseller.network.NetworkAsyncTask;
import com.ykyahwa.bookbestseller.network.NetworkListner;

import org.junit.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by eokhyunlee on 2016. 1. 21..
 */
@SmallTest
public class NetworkTest implements NetworkListner {
    private BookListData data;
    @Before
    public void request(){
        NetworkAsyncTask networkManager = new NetworkAsyncTask(this);
        networkManager.execute((Void[]) null);

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
    public void onBestSelletResponseData(BookListData data) {
        this.data = data;


    }
}
