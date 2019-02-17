package com.example.nolanbonnie.social_media;

import android.app.AppOpsManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private TextView mTextMessage;

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent i = new Intent(Main2Activity.this, Reduce4Activity.class);
                    startActivity(i);
                    break;
                case R.id.navigation_dashboard:
                    break;
                case R.id.navigation_notifications:
                    Intent j = new Intent(Main2Activity.this, News3Activity.class);
                    startActivity(j);
                    break;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (!isAccessGranted()) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            startActivity(intent);
        }

        final UsageStatsManager usageStatsManager=(UsageStatsManager)this.getSystemService(Context.USAGE_STATS_SERVICE);
        // Context.USAGE_STATS_SERVICE);

        Calendar beginCal = Calendar.getInstance();
        beginCal.set(Calendar.DATE, 10);
        beginCal.set(Calendar.MONTH, 2);
        beginCal.set(Calendar.YEAR, 2019);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.DATE, 16);
        endCal.set(Calendar.MONTH, 2);
        endCal.set(Calendar.YEAR, 2019);

        final List<UsageStats> queryUsageStats=usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST,
                System.currentTimeMillis() - (86400000*7), System.currentTimeMillis()); //1 week data pull

        HashMap<String, Long> App_Data_Week = new HashMap<String, Long>();

        String[] strs = {"instagram", "snapchat", "facebook", "twitter", "reddit", "pinterest",
                "linkedin", "tumblr", "flickr", "periscope", "youtube","contacts", "vending", "frontpage", ""};

        List<String> media_sites = Arrays.asList(strs);

        //NEED TO CHECK THESE ID's ARE CORRECT

        for (UsageStats usageStats : queryUsageStats)
            if (usageStats.getTotalTimeInForeground() > 10000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");

                if (!split_name[0].matches("com")){
                    Log.e("qwerty", Arrays.toString(split_name));
                    Log.e("qwerty", split_name[0].getClass().getName());
                    //Log.d("qwerty", split_name[split_name.length-1]);
                   // Log.d("qwerty", )
                    continue;
                    //Needs to skip to next for loop iteration
                }

                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }

                App_Data_Week.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));



                TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                textbox.setText(textbox.getText() + "\n" + split_name[0] + " "+ app_name + ": "
                        + (usageStats.getTotalTimeInForeground() / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());


            }
            Log.e("hash_data", App_Data_Week.toString());
//            for (String i : App_Data_Week.keySet()) {
//                if (!media_sites.contains(i)){
//                    App_Data_Week.remove(i);
//                }
//            }
            App_Data_Week.keySet().retainAll(media_sites);
            Log.e("hash_data", App_Data_Week.toString());

    }

    private boolean isAccessGranted() {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName);
            }
            return (mode == AppOpsManager.MODE_ALLOWED);

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}