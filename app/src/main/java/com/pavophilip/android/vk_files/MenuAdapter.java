package com.pavophilip.android.vk_files;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Philip on 08.01.2015.
 */
public class MenuAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    List<MenuItem> items  = Collections.emptyList();

    public MenuAdapter(Context context, List<MenuItem> items){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }
   /* @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.menu_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public void clearMenu(){

    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        MenuItem item = this.items.get(position);
        holder.icon.setImageResource(item.iconId);
        holder.title.setText(item.title);
    }
     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            icon = (ImageView) itemView.findViewById(R.id.item_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //v.setSelected(true);
            Toast.makeText(context, "CLicked"+getPosition(), Toast.LENGTH_LONG).show();
        }
    }
    */

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.menu_item, parent, false);
        }

        MenuItem item = getItem(position);

        ((ImageView) view.findViewById(R.id.item_icon)).setImageResource(item.iconId);
        ((TextView) view.findViewById(R.id.item_title)).setText(item.title);

        return view;
    }
}
