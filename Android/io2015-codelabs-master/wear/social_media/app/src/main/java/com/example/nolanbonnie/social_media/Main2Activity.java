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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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

        final UsageStatsManager usageStatsManager = (UsageStatsManager) this.getSystemService(Context.USAGE_STATS_SERVICE);
        // Context.USAGE_STATS_SERVICE);

        Calendar beginCal = Calendar.getInstance();
        beginCal.set(Calendar.DATE, 10);
        beginCal.set(Calendar.MONTH, 2);
        beginCal.set(Calendar.YEAR, 2019);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Calendar.DATE, 16);
        endCal.set(Calendar.MONTH, 2);
        endCal.set(Calendar.YEAR, 2020);

        long time = System.currentTimeMillis();
        long day = 86400000; //in ms

        final List<UsageStats> queryUsageStats7 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day * 7), time - (day * 6)); //1 week data pull
        final List<UsageStats> queryUsageStats6 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day * 6), time - (day * 5));
        final List<UsageStats> queryUsageStats5 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day * 5), time - (day * 4));
        final List<UsageStats> queryUsageStats4 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day * 4), time - (day * 3));
        final List<UsageStats> queryUsageStats3 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day * 3), time - (day * 2));
        final List<UsageStats> queryUsageStats2 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day * 2), time - (day));
        final List<UsageStats> queryUsageStats1 = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,
                time - (day), time);


        //Log.e("query_stats", queryUsageStats2.toString());
        //Log.e("query_stats", queryUsageStats1.toString());


        String[] strs = {"instagram", "snapchat", "facebook", "twitter", "reddit", "pinterest",
                "linkedin", "tumblr", "flickr", "periscope", "youtube", "contacts", "vending", "frontpage"};
        List<String> media_sites = Arrays.asList(strs);


        HashMap<String, Long> App_Data_Day1 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats1)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day1.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }

        Log.e("hash_data", App_Data_Day1.toString());
        // App_Data_Day1.keySet().retainAll(media_sites);      // ! parse out non-social media titles
        Log.e("hash_data", App_Data_Day1.toString());

        TextView textbox = (TextView) findViewById(R.id.editText);
        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "\n\nDAY 2\n\n", TextView.BufferType.SPANNABLE);

        HashMap<String, Long> App_Data_Day2 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats2)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day2.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                //TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }

        Log.e("hash_data", App_Data_Day2.toString());
        // App_Data_Day2.keySet().retainAll(media_sites);      // ! parse out non-social media titles
        Log.e("hash_data", App_Data_Day2.toString());

        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "\n\nDAY 3\n\n", TextView.BufferType.SPANNABLE);

        HashMap<String, Long> App_Data_Day3 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats3)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day3.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                //TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }

        Log.e("hash_data", App_Data_Day3.toString());
        // App_Data_Day3.keySet().retainAll(media_sites);      // ! parse out non-social media titles
        Log.e("hash_data", App_Data_Day3.toString());

        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "\n\nDAY 4\n\n", TextView.BufferType.SPANNABLE);

        HashMap<String, Long> App_Data_Day4 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats4)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day4.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                //TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }

        Log.e("hash_data", App_Data_Day4.toString());
        // App_Data_Day4.keySet().retainAll(media_sites);      // ! parse out non-social media titles
        Log.e("hash_data", App_Data_Day4.toString());

        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "\n\nDAY 5\n\n", TextView.BufferType.SPANNABLE);

        HashMap<String, Long> App_Data_Day5 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats5)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day5.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                //TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }

        Log.e("hash_data", App_Data_Day5.toString());
        // App_Data_Day5.keySet().retainAll(media_sites);      // ! parse out non-social media titles
        Log.e("hash_data", App_Data_Day5.toString());

        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "\n\nDAY 6\n\n", TextView.BufferType.SPANNABLE);

        HashMap<String, Long> App_Data_Day6 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats6)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day6.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                //TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }

        Log.e("hash_data", App_Data_Day6.toString());
        // App_Data_Day6.keySet().retainAll(media_sites);      // ! parse out non-social media titles
        Log.e("hash_data", App_Data_Day6.toString());

        textbox.setMovementMethod(new ScrollingMovementMethod());
        textbox.setText(textbox.getText() + "\n\nDAY 7\n\n", TextView.BufferType.SPANNABLE);



        HashMap<String, Long> App_Data_Day7 = new HashMap<String, Long>();
        for (UsageStats usageStats : queryUsageStats7)

            if (usageStats.getTotalTimeInForeground() > 60000) {  // 60000ms, = 1 minute
                String app_parts = usageStats.getPackageName();
                String[] split_name = app_parts.split("\\.");
                if (!split_name[0].matches("com")) {
                    continue;
                }
                String app_name = null;
                try {
                    app_name = split_name[split_name.length - 1];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                App_Data_Day7.put(app_name, (usageStats.getTotalTimeInForeground() / (1000 * 60)));
                //TextView textbox = (TextView) findViewById(R.id.editText);
                textbox.setMovementMethod(new ScrollingMovementMethod());
                double y = (double) usageStats.getTotalTimeInForeground();
                DecimalFormat df = new DecimalFormat("#.##");
                Log.e("long_int", "value" + (y / 2));
                if ((usageStats.getTotalTimeInForeground() / (1000 * 60)) < 60) {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60)) + " Minutes", TextView.BufferType.SPANNABLE);
                } else {
                    textbox.setText(textbox.getText() + "\n" + app_name + ": "
                            + df.format(y / (1000 * 60 * 60)) + " Hours", TextView.BufferType.SPANNABLE);
                }
                Log.i("Stats", usageStats.getPackageName() + " " + usageStats.getTotalTimeInForeground());
            }


        Log.e("hash_data", App_Data_Day7.toString());
       // App_Data_Day7.keySet().retainAll(media_sites);      // ! parse out non-social media titles
       // Log.e("hash_data", App_Data_Day7.toString());
        Log.e("hash_data", sortHashMapByValues(App_Data_Day7).toString());



        LinkedHashMap<String, Long> day1 = sortHashMapByValues(App_Data_Day1);
        LinkedHashMap<String, Long> day2 = sortHashMapByValues(App_Data_Day2);
        LinkedHashMap<String, Long> day3 = sortHashMapByValues(App_Data_Day3);
        LinkedHashMap<String, Long> day4 = sortHashMapByValues(App_Data_Day4);
        LinkedHashMap<String, Long> day5 = sortHashMapByValues(App_Data_Day5);
        LinkedHashMap<String, Long> day6 = sortHashMapByValues(App_Data_Day6);
        LinkedHashMap<String, Long> day7 = sortHashMapByValues(App_Data_Day7);


        //List<LinkedHashMap<String, Long>> week = {day1, day2, day3, day4, day5, day6, day7};
        ArrayList<String> Key1 = new ArrayList<String>();
        for (String i : day1.keySet()){
            Key1.add(i);
        }
        ArrayList<Long> Value1 = new ArrayList<Long>();
        for (Long i : day1.values()){
            Value1.add(i);
        }
        ArrayList<String> Key2 = new ArrayList<String>();
        for (String i : day2.keySet()){
            Key2.add(i);
        }
        ArrayList<Long> Value2 = new ArrayList<Long>();
        for (Long i : day2.values()){
            Value2.add(i);
        }
        ArrayList<String> Key3 = new ArrayList<String>();
        for (String i : day3.keySet()){
            Key3.add(i);
        }
        ArrayList<Long> Value3 = new ArrayList<Long>();
        for (Long i : day3.values()){
            Value3.add(i);
        }
        ArrayList<String> Key4 = new ArrayList<String>();
        for (String i : day4.keySet()){
            Key4.add(i);
        }
        ArrayList<Long> Value4 = new ArrayList<Long>();
        for (Long i : day4.values()){
            Value4.add(i);
        }
        ArrayList<String> Key5 = new ArrayList<String>();
        for (String i : day5.keySet()){
            Key5.add(i);
        }
        ArrayList<Long> Value5 = new ArrayList<Long>();
        for (Long i : day5.values()){
            Value5.add(i);
        }
        ArrayList<String> Key6 = new ArrayList<String>();
        for (String i : day6.keySet()){
            Key6.add(i);
        }
        ArrayList<Long> Value6 = new ArrayList<Long>();
        for (Long i : day6.values()){
            Value6.add(i);
        }
        ArrayList<String> Key7 = new ArrayList<String>();
        for (String i : day7.keySet()){
            Key7.add(i);
        }
        ArrayList<Long> Value7 = new ArrayList<Long>();
        for (Long i : day7.values()){
            Value7.add(i);
        }

        ArrayList<ArrayList<String>> allStringArrays = new ArrayList<ArrayList<String>>();
        allStringArrays.add(Key1);
        allStringArrays.add(Key2);
        allStringArrays.add(Key3);
        allStringArrays.add(Key4);
        allStringArrays.add(Key5);
        allStringArrays.add(Key6);
        allStringArrays.add(Key7);

        ArrayList<ArrayList<Long>> allLongArrays = new ArrayList<ArrayList<Long>>();
        allLongArrays.add(Value1);
        allLongArrays.add(Value2);
        allLongArrays.add(Value3);
        allLongArrays.add(Value4);
        allLongArrays.add(Value5);
        allLongArrays.add(Value6);
        allLongArrays.add(Value7);

        for (ArrayList<String> arr : allStringArrays){
            if (arr.size() > 10){
                while(arr.size() > 10)
                    arr.remove(0);
            }
        }

        for (ArrayList<Long> arr : allLongArrays){
            if (arr.size() > 10){
                while(arr.size() > 10)
                    arr.remove(0);
            }
        }

        BarChart chart = (BarChart) findViewById(R.id.chart);

        List<Long> data = allLongArrays.get(0);

        List<BarEntry> entries = new ArrayList<>();

        int i = 1;
        for (Long l : data) {
            entries.add(new BarEntry((float) i, (float) l));
            i += 1;
        }

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data2 = new BarData(set);
        data2.setBarWidth(1f); // set custom bar width
        chart.setData(data2);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh








    }

    public LinkedHashMap<String, Long> sortHashMapByValues(
            HashMap<String, Long> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Long> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Long> sortedMap =
                new LinkedHashMap<>();

        Iterator<Long> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Long val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Long comp1 = passedMap.get(key);
                Long comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
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