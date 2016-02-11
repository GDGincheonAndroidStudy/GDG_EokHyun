package com.ykyahwa.bookbestseller.main.component;

/**
 * Created by eokhyunlee on 2016. 2. 11..
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView;
import co.moonmonkeylabs.realmsearchview.ClearableEditText;
import co.moonmonkeylabs.realmsearchview.R.id;
import co.moonmonkeylabs.realmsearchview.R.layout;
import co.moonmonkeylabs.realmsearchview.R.string;
import co.moonmonkeylabs.realmsearchview.R.styleable;
import co.moonmonkeylabs.realmsearchview.RealmSearchAdapter;

public class CustomRealmSearchView extends LinearLayout {
    private RealmRecyclerView realmRecyclerView;
    private ClearableEditText searchBar;
    private RealmSearchAdapter adapter;

    public CustomRealmSearchView(Context context) {
        super(context);
        this.init(context, (AttributeSet)null);
    }

    public CustomRealmSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init(context, attrs);
    }

    public CustomRealmSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, layout.realm_search_view, this);
        this.setOrientation(LinearLayout.VERTICAL);
        this.realmRecyclerView = (RealmRecyclerView)this.findViewById(id.realm_recycler_view);
        this.searchBar = (ClearableEditText)this.findViewById(id.search_bar);
        this.initAttrs(context, attrs);
        this.searchBar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                CustomRealmSearchView.this.adapter.filter(s.toString());
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, styleable.RealmSearchView);
        int hintTextResId = typedArray.getResourceId(styleable.RealmSearchView_rsvHint, string.rsv_default_search_hint);
        this.searchBar.setHint(hintTextResId);
        int clearDrawableResId = typedArray.getResourceId(styleable.RealmSearchView_rsvClearDrawable, -1);
        if(clearDrawableResId != -1) {
            this.searchBar.setClearDrawable(this.getResources().getDrawable(clearDrawableResId));
        }

        typedArray.recycle();
    }

    public void setAdapter(RealmSearchAdapter adapter) {
        this.adapter = adapter;
        this.realmRecyclerView.setAdapter(adapter);
        this.adapter.filter("");
    }

    public int getVisibleSearchBar() {
        return searchBar.getVisibility();
    }
    public void setVisibleSearchBar(int visible) {
        if (visible == View.VISIBLE) {
            searchBar.setVisibility(View.VISIBLE);
        } else {
            searchBar.setVisibility(View.GONE);
        }


    }
}
