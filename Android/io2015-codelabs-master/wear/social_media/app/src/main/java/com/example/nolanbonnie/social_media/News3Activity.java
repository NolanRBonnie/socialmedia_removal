package com.example.nolanbonnie.social_media;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class News3Activity extends AppCompatActivity {

    private TextView mTextMessage;

    private static final String TAG = "News3Activity";

    private ListView mListView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(News3Activity.this, Reduce4Activity.class);
                    startActivity(i);
                    break;
                case R.id.navigation_dashboard:
                    Intent j = new Intent(News3Activity.this, Main2Activity.class);
                    startActivity(j);
                    break;
                case R.id.navigation_notifications:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news3);

        mListView = (ListView) findViewById(R.id.listView);

        ArrayList<Card> list = new ArrayList<>();

        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Arizona Dessert"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Bamf"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Colorado Mountains"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Cork"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "DavenPort California"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Denmark Austrailia"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Foggy Iceland"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Havasu Falls"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Hawaii RainForest"));
        list.add(new Card("drawable://" + R.drawable.sm , "www.google.com", "NewFoundLand Ice"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Oregon Greens"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Smokey Mountain"));
        list.add(new Card("drawable://" + R.drawable.sm, "www.google.com", "Yosemite"));

        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout, list);
        mListView.setAdapter(adapter);
    }

}
