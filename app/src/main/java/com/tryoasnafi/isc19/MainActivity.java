package com.tryoasnafi.isc19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tryoasnafi.isc19.activity.AboutActivity;
import com.tryoasnafi.isc19.adapter.ListIsc19Adapater;
import com.tryoasnafi.isc19.model.Isc19;
import com.tryoasnafi.isc19.model.Isc19Data;

import java.util.ArrayList;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvISC19;
    private ArrayList<Isc19> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Implement custom font setting in Main Activity
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/IBMPlexSans-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        rvISC19 = findViewById(R.id.rv_isc19);
        rvISC19.setHasFixedSize(true);

        list.addAll(Isc19Data.getListData());
        showRecyclerList();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent i = new Intent(this, MainActivity.class);  //your class
        startActivity(i);
        finish();
    }

    private void showRecyclerList() {
        rvISC19.setLayoutManager(new LinearLayoutManager(this));
        ListIsc19Adapater listHeroAdapter = new ListIsc19Adapater(list, this);
        rvISC19.setAdapter(listHeroAdapter);
    }

    // Menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_home:
                onRestart();
                break;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
        }
    }

    // Custom font setting
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
