package com.ykyahwa.bookbestseller.network;

import com.ykyahwa.bookbestseller.data.BookListData;

/**
 * Created by ehlee on 2016-01-15.
 */
public interface NetworkListner {
    void onBestSelletResponseData(BookListData data);
}
