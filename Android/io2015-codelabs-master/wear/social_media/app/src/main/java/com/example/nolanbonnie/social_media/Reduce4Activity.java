package com.example.nolanbonnie.social_media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;

public class Reduce4Activity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_dashboard:
                    Intent i = new Intent(Reduce4Activity.this, Main2Activity.class);
                    startActivity(i);
                    break;
                case R.id.navigation_notifications:
                    Intent j = new Intent(Reduce4Activity.this, News3Activity.class);
                    startActivity(j);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduce4);

        TextView textbox = (TextView) findViewById(R.id.editText);
        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "With the development of technology and social engineering, social media is able to more easily retain people and their attention.  " +
                "The average American spends more than 10 hours per day across all social media.  That is nearly more than half a day spent on an electric device.  " +
                "Around 68% of Americans have a facebook account in 2017 and in 2018 Instagram had hit 1 billions active users.  More and more people are on social " +
                "media, and the amount of time they spend there is increasing. " +
                "There are many people who could be considered addicted to social media.\n\nLike drugs or alcohol, " +
                "people can become addicted to social media.  There is an estimate of 210 million people who suffer " +
                "from internet and social media addiction.  This addiction can cause depression, anxiety, " +
                "lower self-esteem, lower self-control, and others.  Addiction is bad whether it be from drugs " +
                "or from internet usage.\n\nThe inspiration for this app came from a small, yet growing " +
                "movement supporting digital wellbeing, or the desire finding a balance between the world " +
                "and technology. With new apps coming to market every day, now more than ever there is a " +
                "large temptation to be lost in this digital world. However, when these technologies start " +
                "becoming an addiction, most people aren’t able to recognize the actions they should take to " +
                "combat their obsessions. It has become too easy to escape from the hardships of life and " +
                "divulge into social media algorithms that are designed to keep you on their platform for as " +
                "long as possible. Humans are losing their humanity, and it’ll be up to individuals to " +
                "recognize that perhaps giving up some of their technology usage would be better for their " +
                "wellbeing.", TextView.BufferType.SPANNABLE);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
