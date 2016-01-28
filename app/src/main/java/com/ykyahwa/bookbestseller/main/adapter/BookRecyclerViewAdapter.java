package com.ykyahwa.bookbestseller.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ykyahwa.bookbestseller.R;
import com.ykyahwa.bookbestseller.data.BookData;

import java.util.ArrayList;

/**
 * Created by eokhyunlee on 2016. 1. 23..
 */
public class BookRecyclerViewAdapter extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

    private ArrayList<BookData> bookDataList;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView title;
        TextView author;
        ImageView image;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.BOOK_ITEM_CARDVIEW_TITLE);
            author = (TextView) v.findViewById(R.id.BOOK_ITEM_CARDVIEW_AUTHOR);
            image = (ImageView) v.findViewById(R.id.BOOK_ITEM_CARDVIEW_IMAGE);
        }
    }

    public BookRecyclerViewAdapter(Context context, ArrayList<BookData> bookDataList) {
        this.context = context;
        this.bookDataList = bookDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookData data = bookDataList.get(position);

        holder.title.setText(data.getTitle());
        holder.author.setText(data.getAuthor());
        Picasso.with(context).load(data.getCoverSmallUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return bookDataList.size();
    }


}
