package com.example.kim_wonhee.a170413;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kim_wonhee on 2017. 4. 13..
 */

public class RestAdpater extends BaseAdapter {

    ArrayList<Data> data;
    Context c;

    public RestAdpater(Context c, ArrayList<Data> data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.textname);
        TextView textView2 = (TextView)convertView.findViewById(R.id.texttel);
        ImageView image = (ImageView)convertView.findViewById(R.id.imageView);

        textView.setText(data.get(position).getName());
        textView2.setText(data.get(position).getPhone());

        if (data.get(position).getFood().equals("치킨")) {
            image.setImageResource(R.drawable.chicken);
        } else if (data.get(position).getFood().equals("피자")) {
            image.setImageResource(R.drawable.pizza);
        } else if (data.get(position).getFood().equals("버거")) {
            image.setImageResource(R.drawable.burger);
        }

        return convertView;
    }
}
