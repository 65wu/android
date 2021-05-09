package com.example.android.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.MainActivity;
import com.example.android.R;
import com.example.android.entity.Spirits;

import java.util.List;

public class SpiritsAdapter extends BaseAdapter {
    private final List<Spirits> mData;
    private final Context mContext;

    public SpiritsAdapter(List<Spirits> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mData.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.content_simple_main,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.txt_name);
        img_icon.setImageBitmap(new FileHelper().loadImageBitmap(
                mContext, mData.get(position).get_id() + ""));
        txt_aName.setText(mData.get(position).get_name());
        return convertView;
    }
}
