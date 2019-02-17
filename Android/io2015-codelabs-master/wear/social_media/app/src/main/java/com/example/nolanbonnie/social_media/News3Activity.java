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

        list.add(new Card("drawable://" + R.drawable.a1, "https://www.liebertpub.com/doi/pdf/10.1089/cpb.1998.1.25", "By KIMBERLY S. YOUNG, Psy.D. and ROBERT C. ROGERS "));
        list.add(new Card("drawable://" + R.drawable.a2, "https://www.liebertpub.com/doi/pdf/10.1089/cpb.2004.7.384", "By KIMBERLY S. YOUNG, Psy.D. and ROBERT C. ROGERS "));
        list.add(new Card("drawable://" + R.drawable.a3, "https://www.liebertpub.com/doi/pdf/10.1089/109493103321640338", "By KIMBERLY S. YOUNG, Psy.D. and ROBERT C. ROGERS "));
        list.add(new Card("drawable://" + R.drawable.a4, "https://www.liebertpub.com/doi/pdf/10.1089/cyber.2014.0070", "By Igor Pantic, MD, PhD "));
        list.add(new Card("drawable://" + R.drawable.a5, "https://www.liebertpub.com/doi/pdf/10.1089/cyber.2014.0070", "By Igor Pantic, MD, PhD "));


        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.card_layout, list);
        mListView.setAdapter(adapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_notifications);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
