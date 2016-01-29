package com.ykyahwa.bookbestseller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ykyahwa.bookbestseller.data.BookData;
import com.ykyahwa.bookbestseller.data.BookListData;
import com.ykyahwa.bookbestseller.data.BookRealmData;
import com.ykyahwa.bookbestseller.main.adapter.BookListAdapter;
import com.ykyahwa.bookbestseller.main.adapter.BookRealmSearchAdapter;
import com.ykyahwa.bookbestseller.main.adapter.BookRecyclerViewAdapter;
import com.ykyahwa.bookbestseller.network.NetworkListner;
import com.ykyahwa.bookbestseller.network.NetworkRetrofit;

import java.util.ArrayList;
import java.util.List;

import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView bookListView;

    private RecyclerView bookRecyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    private BookListAdapter bookListAdapter;
    private ArrayList<BookData> bookDataList = new ArrayList<>();

    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        initListView();
//        initRecyclerView();
        initRealmSearchView();

        //AsyncTask
//        new NetworkAsyncTask(networkListner).execute((Void[]) null);
        new NetworkRetrofit().request(callback);

    }

    private void initListView() {
        bookListView = (ListView) findViewById(R.id.MAIN_LV_BOOK_LIST);
        bookListAdapter = new BookListAdapter(bookDataList);
        bookListView.setAdapter(bookListAdapter);
        bookListView.setOnItemClickListener(itemClickListener);
    }

    private void initRecyclerView() {
        bookRecyclerView = (RecyclerView) findViewById(R.id.MAIN_RV_BOOK_LIST);
        bookRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        bookRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        // specify an adapter (see also next example)
        recyclerViewAdapter = new BookRecyclerViewAdapter(this, bookDataList);
        bookRecyclerView.setAdapter(recyclerViewAdapter);
    }

    private void initRealmSearchView() {
        resetRealm();

        RealmSearchView realmSearchView = (RealmSearchView) findViewById(R.id.search_view);

        realm = Realm.getInstance(this);
        BookRealmSearchAdapter adapter = new BookRealmSearchAdapter(this, realm, "title");
        realmSearchView.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
            realm = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private NetworkListner networkListner = new NetworkListner() {
        @Override
        public void onBestSelletResponseData(final BookListData data) {

            Handler h = new Handler(getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {

                    dataChange(data.getItem());
                }
            });

        }
    };

    private Callback callback = new Callback<BookListData>() {
        @Override
        public void onResponse(Response<BookListData> response) {
            Log.d("TEST","onResponse");
            dataChange(response.body().getItem());
        }

        @Override
        public void onFailure(Throwable t) {
            Log.d("TEST","onFailure");

        }
    };

    private void dataChange(ArrayList<BookData> newBookDataList) {
        bookDataList.clear();


        bookDataList.addAll(newBookDataList);
//        bookListAdapter.notifyDataSetChanged();


        List<BookRealmData> realmDataList = new ArrayList<>();
        for (BookData serverBookData : newBookDataList) {
            realmDataList.add(new BookRealmData(serverBookData));
        }

        Realm realm = Realm.getInstance(this);
        realm.beginTransaction();
        realm.copyToRealm(realmDataList);
        realm.commitTransaction();
        realm.close();
    }

    private void resetRealm() {
        RealmConfiguration realmConfig = new RealmConfiguration
                .Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.deleteRealm(realmConfig);
    }
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String url = bookDataList.get(position).getMobileLink();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    };

}
