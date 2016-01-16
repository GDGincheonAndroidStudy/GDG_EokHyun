package com.ykyahwa.bookbestseller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ykyahwa.bookbestseller.data.BookData;
import com.ykyahwa.bookbestseller.data.BookListData;
import com.ykyahwa.bookbestseller.main.adapter.BookListAdapter;
import com.ykyahwa.bookbestseller.network.NetworkListner;
import com.ykyahwa.bookbestseller.network.NetworkManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView bookListView;
    private BookListAdapter bookListAdapter;
    private ArrayList<BookData> bookDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        initialize();
        new NetworkManager(networkListner).start();

    }

    private void initialize() {
        bookListView = (ListView) findViewById(R.id.MAIN_LV_BOOK_LIST);
        bookListAdapter = new BookListAdapter(bookDataList);
        bookListView.setAdapter(bookListAdapter);
        bookListView.setOnItemClickListener(itemClickListener);
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
                    bookDataList.clear();

                    bookDataList.addAll(data.getItem());
                    bookListAdapter.notifyDataSetChanged();
                }
            });

        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String url = bookDataList.get(position).getMobileLink();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    };
}
