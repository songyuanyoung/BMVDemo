package com.example.zach.bmvdemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zach.bmvdemo.Data.Model.Location;
import com.example.zach.bmvdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangwenpurdue on 8/20/2017.
 */

public class LocationFragmentAdapter extends RecyclerView.Adapter<LocationFragmentAdapter.ViewHolder> {
    private Context context;
    private List<Location> list;

    public LocationFragmentAdapter(Context context, List<Location> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_location, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Location location = list.get(position);
        holder.ID.setText(location.getID() + "");
        holder.Name.setText(location.getName());
        holder.Latitude.setText(location.getLatitude() + "");
        holder.Longitude.setText(location.getLongitude() + "");
        holder.Address.setText(location.getAddress());
        holder.ArrivalTime.setText(location.getArrivalTime());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void UpdateList(List<Location> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ID)
        TextView ID;
        @BindView(R.id.Name)
        TextView Name;
        @BindView(R.id.Latitude)
        TextView Latitude;
        @BindView(R.id.Longitude)
        TextView Longitude;
        @BindView(R.id.Address)
        TextView Address;
        @BindView(R.id.ArrivalTime)
        TextView ArrivalTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
