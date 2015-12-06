package com.google.davidsuzukinaturechallenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.davidsuzukinaturechallenge.R;
import com.google.davidsuzukinaturechallenge.models.Park;

/**
 * Created by jenna on 15-12-06.
 */
public class ParkAdapter extends RecyclerView.Adapter<ParkAdapter.ViewHolder> {

    private Park[] mParks;

    public ParkAdapter(Park[] parks) {
        super();
        mParks = parks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.park_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Park park = mParks[i];
        viewHolder.parkName.setText(park.getName());

    }


    @Override
    public int getItemCount() {
        return mParks.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView parkName;

        public ViewHolder(View itemView) {
            super(itemView);
            parkName = (TextView)itemView.findViewById(R.id.parkName);

        }
    }
}
