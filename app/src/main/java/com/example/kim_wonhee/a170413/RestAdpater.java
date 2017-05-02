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
import java.util.logging.Filter;

/**
 * Created by kim_wonhee on 2017. 4. 13..
 */

public class RestAdpater extends BaseAdapter {


    ArrayList<Data> stores;
    ArrayList<Data> searchdata = new ArrayList<Data>();
    ArrayList<String> store;
    Context c;

    int NAME = 0;
    int FOOD = 1;
    int CHECK = 0;

    TextView textView;
    TextView textView2;
    ImageView image;
    CheckBox checkBox;



    public RestAdpater(Context c, ArrayList<Data> stores) {
        this.c = c;
        this.stores = stores;
    }

    @Override
    public int getCount() {
        return stores.size();
    }

    @Override
    public Object getItem(int position) {
        return stores.get(position);
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

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                stores.get(position).isSelected = isChecked;
            }
        });


        if (CHECK == 1)
            checkBox.setVisibility(View.VISIBLE);
        else if (CHECK == 0) {
            checkBox.setChecked(false);
            checkBox.setVisibility(View.INVISIBLE);
        }

        textView.setText(stores.get(position).getName());
        textView2.setText(stores.get(position).getPhone());

        if (stores.get(position).getFood().equals("chicken")) {
            image.setImageResource(R.drawable.chicken);
        } else if (stores.get(position).getFood().equals("pizza")) {
            image.setImageResource(R.drawable.pizza);
        } else if (stores.get(position).getFood().equals("burger")) {
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
            Collections.sort(stores, nameAsc);
            this.notifyDataSetChanged();
        } else if (sortType == FOOD) {
            Collections.sort(stores, foodAsc);
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

    public void filter(String Text) {
        Text = Text.toLowerCase();
        searchdata.clear();
        if (Text.length() == 0) {
            searchdata.addAll(stores);
        } else {
            for (Data one : stores) {
                String name = one.getName().toLowerCase();
                if (name.contains(Text))
                    searchdata.add(one);
            }
        }
        this.notifyDataSetChanged();
    }


}
