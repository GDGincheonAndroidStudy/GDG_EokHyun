package com.ykyahwa.bookbestseller.main.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ykyahwa.bookbestseller.R;
import com.ykyahwa.bookbestseller.data.BookData;

/**
 * Created by ehlee on 2016-01-15.
 */
public class BookItemView extends LinearLayout {
    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        View.inflate(getContext(), R.layout.book_item, this);
    }

    public void setData(BookData bookData) {
        ((TextView)findViewById(R.id.BOOK_ITEM_TITLE)).setText(bookData.getTitle());
        ((TextView)findViewById(R.id.BOOK_ITEM_AUTHOR)).setText(bookData.getAuthor());
        Picasso.with(getContext()).load(bookData.getCoverSmallUrl()).into((ImageView)findViewById(R.id.BOOK_ITEM_IMAGE));

    }

}
