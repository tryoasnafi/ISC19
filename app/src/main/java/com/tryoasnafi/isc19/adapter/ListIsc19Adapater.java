package com.tryoasnafi.isc19.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tryoasnafi.isc19.R;
import com.tryoasnafi.isc19.activity.DetailActivity;
import com.tryoasnafi.isc19.model.Isc19;

import java.util.ArrayList;

public class ListIsc19Adapater extends RecyclerView.Adapter<ListIsc19Adapater.ListViewHolder> {
    private ArrayList<Isc19> listIsc19;
    private Context c;

    public ListIsc19Adapater(ArrayList<Isc19> list, Context c) {
        this.listIsc19 = list;
        this.c = c;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_isc19, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        final Isc19 isc19 = listIsc19.get(position);
        final String title = isc19.getTitle();
        final String date = isc19.getDate();
        final String writer = isc19.getWriter();
        final int thumbnail = isc19.getThumbnail();

        Glide.with(holder.itemView.getContext())
                .load(thumbnail)
                .apply(new RequestOptions().override(120, 120))
                .into(holder.imgThumbnail);
        holder.tvTitle.setText(title);
        holder.tvDate.setText(date);
        holder.tvWriter.setText(writer);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, "" + isc19.getTitle(), Toast.LENGTH_SHORT).show();
                // Passing Posisi Data
                openDetail(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listIsc19.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView tvTitle, tvDate, tvWriter;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgThumbnail = itemView.findViewById(R.id.img_thumbnail);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvWriter = itemView.findViewById(R.id.tv_writer);
        }
    }

    private void openDetail(int position) {
        Intent i = new Intent(c, DetailActivity.class);
        i.putExtra("POSITION", position);
        c.startActivity(i);
    }
}
