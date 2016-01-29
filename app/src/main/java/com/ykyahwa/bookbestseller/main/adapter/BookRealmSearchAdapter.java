package com.ykyahwa.bookbestseller.main.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.ykyahwa.bookbestseller.data.BookRealmData;
import com.ykyahwa.bookbestseller.main.component.BookItemView;

import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;
import io.realm.Realm;
import io.realm.RealmViewHolder;

/**
 * Created by ehlee on 2016-01-29.
 */
public class BookRealmSearchAdapter extends RealmSearchAdapter<BookRealmData, BookRealmSearchAdapter.ViewHolder> {


    public BookRealmSearchAdapter(Context context, Realm realm, String filterKey) {
        super(context, realm, filterKey);
    }

    public class ViewHolder extends RealmViewHolder {

        private final BookItemView bookItemView;

        public ViewHolder(BookItemView bookItemView) {
            super(bookItemView);
            this.bookItemView = bookItemView;
        }
    }

    @Override
    public BookRealmSearchAdapter.ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder vh = new ViewHolder(new BookItemView(viewGroup.getContext()));
        return vh;
    }

    @Override
    public void onBindRealmViewHolder(BookRealmSearchAdapter.ViewHolder viewHolder, int i) {
        final BookRealmData bookData = realmResults.get(i);
        viewHolder.bookItemView.setData(bookData);
    }

    @Override
    public BookRealmSearchAdapter.ViewHolder convertViewHolder(RealmViewHolder realmViewHolder) {
        return ViewHolder.class.cast(realmViewHolder);
    }
}
