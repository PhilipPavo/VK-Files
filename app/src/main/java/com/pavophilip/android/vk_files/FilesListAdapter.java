package com.pavophilip.android.vk_files;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by Philip on 10.01.2015.
 */
public class FilesListAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private Context context;
    List<FileItem> items  = Collections.emptyList();

    public FilesListAdapter(Context context, List<FileItem> items){
        this.items = items;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public FileItem getItem(int position) {
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
            view = inflater.inflate(R.layout.file_item_my, parent, false);
        }

        FileItem item = this.getItem(position);
        item.position = position;
        item.view_icon =  ((ImageView) view.findViewById(R.id.item_icon));
        item.view_title = ((TextView) view.findViewById(R.id.item_title));
        item.view_size = ((TextView) view.findViewById(R.id.item_size));

        item.view_size.setText(Utils.SizeToString(item.size, true));
        item.view_title.setText(item.title);
        int icon_id = Utils.getResId("ic_ext_" + item.ext, R.drawable.class);
        if(icon_id == -1) icon_id = R.drawable.ic_ext_other;
        item.view_icon.setImageResource(icon_id);
        //if(item.photo_100 == null) item.view_icon.setImageResource(R.drawable.ic_menu_docs_my);
        //else new LoadImageTask(position, item).execute();
        return view;
    }
    /*private class LoadImageTask extends AsyncTask<Void, Void, FileItem> {
        int position;
        FileItem file;
        public  LoadImageTask(int position, FileItem item){
            this.position = position;
            this.file = item;
        }
        @Override
        protected FileItem doInBackground(Void... params) {
            if(file.photo_100 == null) {
                file.icon = null;
                return file;
            }
            try {
                URL imageURL = new URL(file.photo_100);
                file.icon = BitmapFactory.decodeStream(imageURL.openStream());
            } catch (IOException e) {
                file.icon = null;
            }

            return file;
        }

        @Override
        protected void onPostExecute(FileItem file) {
            file.view_icon.setImageBitmap(file.icon);
        }
    }*/

}