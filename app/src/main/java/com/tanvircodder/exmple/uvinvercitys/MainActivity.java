package com.tanvircodder.exmple.uvinvercitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.tanvircodder.exmple.uvinvercitys.databaes.DatabaseClient;
import com.tanvircodder.exmple.uvinvercitys.model.Util;
import com.tanvircodder.exmple.uvinvercitys.network.JsonParser;
import com.tanvircodder.exmple.uvinvercitys.network.UrlConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    ViewPagerAdapter mAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        tabLayout = (TabLayout)findViewById(R.id.tab_mode);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Versity");
        tabLayout.getTabAt(1).setText("Search");


    }


}