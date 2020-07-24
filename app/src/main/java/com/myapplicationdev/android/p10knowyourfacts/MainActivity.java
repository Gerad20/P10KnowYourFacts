package com.myapplicationdev.android.p10knowyourfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;


    Button readLater;
    int reqCode = 12345;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewpager1);
        readLater = findViewById(R.id.readLater);

        SharedPreferences prefs = getSharedPreferences("currentScreen",MODE_PRIVATE);



        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new frag1());
        al.add(new frag2());
        al.add(new frag3());

        adapter = new MyFragmentPagerAdapter(fm, al);

        vPager.setAdapter(adapter);

        vPager.setCurrentItem(prefs.getInt("number",0), true);

        readLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR, 8);

                Intent intent = new Intent(MainActivity.this,
                        notification.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });
            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();


        if (id == R.id.previous) {
            if (vPager.getCurrentItem() > 0) {
                int previousPage = vPager.getCurrentItem() - 1;

                vPager.setCurrentItem(previousPage, true);
            }
        } else if (id == R.id.random) {


            Random random = new Random();
            int randomPage = random.nextInt(2);


            vPager.setCurrentItem(randomPage, true);

        } else if (id == R.id.next) {
            int max = vPager.getChildCount();
            if (vPager.getCurrentItem() < max - 1) {
                int nextPage = vPager.getCurrentItem() + 1;
                vPager.setCurrentItem(nextPage, true);


            }
        }

            return super.onOptionsItemSelected(item);



    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("currentScreen",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("number",vPager.getCurrentItem());
        Toast.makeText(getApplicationContext(),vPager.getCurrentItem() + "", Toast.LENGTH_LONG).show();
        editor.commit();
    }
}