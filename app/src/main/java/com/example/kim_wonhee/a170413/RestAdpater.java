package com.example.kim_wonhee.a170413;

import android.content.Context;
import android.media.Image;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by kim_wonhee on 2017. 4. 13..
 */

public class RestAdpater extends BaseAdapter {


    ArrayList<Data> data;
    ArrayList<String> store;
    Context c;

    int NAME = 0;
    int FOOD = 1;
    int CHECK = -1;

    TextView textView;
    TextView textView2;
    ImageView image;
    CheckBox checkBox;


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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(c);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
        }

        textView = (TextView)convertView.findViewById(R.id.textname);
        textView2 = (TextView)convertView.findViewById(R.id.texttel);
        image = (ImageView)convertView.findViewById(R.id.imageView);
        checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);

        if (CHECK == -1)
            checkBox.setVisibility(View.INVISIBLE);
        else if (CHECK == 1)
            checkBox.setVisibility(View.VISIBLE);
        else if (CHECK == 0) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    data.remove(position);
                }
            });
            checkBox.setVisibility(View.INVISIBLE);
        }

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

    Comparator<Data> nameAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };

    Comparator<Data> foodAsc = new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            return o1.getFood().compareToIgnoreCase(o2.getFood());
        }
    };

    public void setSort(int sortType) {
        if (sortType == NAME) {
            Collections.sort(data, nameAsc);
            this.notifyDataSetChanged();
        } else if (sortType == FOOD) {
            Collections.sort(data, foodAsc);
            this.notifyDataSetChanged();
        }
    }

    public void setCheckBox() {
        CHECK = 1;
        this.notifyDataSetChanged();
    }

    public void setRemove() {
        CHECK = 0;
        this.notifyDataSetChanged();

    }

}
