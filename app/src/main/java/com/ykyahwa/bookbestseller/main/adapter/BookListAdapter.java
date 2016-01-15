package com.ykyahwa.bookbestseller.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ykyahwa.bookbestseller.data.BookData;
import com.ykyahwa.bookbestseller.data.BookListData;
import com.ykyahwa.bookbestseller.main.component.BookItemView;

import java.util.ArrayList;

/**
 * Created by ehlee on 2016-01-15.
 */
public class BookListAdapter extends BaseAdapter {
    private ArrayList<BookData> datas;

    public BookListAdapter(ArrayList<BookData> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        if (datas == null) return 0;
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        if (datas == null) return null;

        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new BookItemView(parent.getContext());
        }
        ((BookItemView)convertView).setData(datas.get(position));
        return convertView;
    }
}
