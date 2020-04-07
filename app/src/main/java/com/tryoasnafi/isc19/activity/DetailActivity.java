package com.tryoasnafi.isc19.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tryoasnafi.isc19.R;
import com.tryoasnafi.isc19.model.Isc19;
import com.tryoasnafi.isc19.model.Isc19Data;

import java.util.ArrayList;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ambil data posisi
        Intent i = this.getIntent();
        int pos = i.getExtras().getInt("POSITION");

        //Panggil item berdasarkan posisi
        ArrayList<Isc19> data = Isc19Data.getListData();
        final Isc19 isc19 = data.get(pos);
        final String title = isc19.getTitle();
        final String date = isc19.getDate();
        final String site = isc19.getSiteSource();
        final String category = isc19.getCategory();
        final String writer = isc19.getWriter();
        final String detail = isc19.getDetail();
        final int thumbnail = isc19.getThumbnail();

        TextView titleTxt = findViewById(R.id.tv_item_title);
        TextView dateTxt = findViewById(R.id.tv_item_date);
        TextView siteTxt = findViewById(R.id.tv_item_site);
        TextView categoryTxt = findViewById(R.id.tv_item_category);
        TextView writerTxt = findViewById(R.id.tv_item_writer);
        TextView detailTxt = findViewById(R.id.tv_item_detail);
        ImageView imgThumbnail = findViewById(R.id.img_detail);

        titleTxt.setText(title);
        dateTxt.setText(date);
        siteTxt.setText(site);
        siteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = getApplicationContext();
                Toast.makeText(c, "Sumber : https://www." + isc19.getSiteSource(), Toast.LENGTH_SHORT).show();
            }
        });
        categoryTxt.setText(category);
        writerTxt.setText(writer);
        detailTxt.setText(detail);
        Glide.with(this)
                .load(thumbnail)
                .apply(new RequestOptions().override(600, 600))
                .into(imgThumbnail);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = this.getIntent();
        int pos = i.getExtras().getInt("POSITION");
        ArrayList<Isc19> data = Isc19Data.getListData();
        Isc19 isc19 = data.get(pos);

        if (item.getItemId() == R.id.action_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Informasi Seputar Covid-19 ~ " + isc19.getTitle());
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
