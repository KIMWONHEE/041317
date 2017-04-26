package com.example.kim_wonhee.a170413;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    ListView listview;

    ArrayList<String> store = new ArrayList<String>();
    ArrayList<Data> stores = new ArrayList<Data>();
    RestAdpater adapter;

    Data dataset;

    static final int REQUEST_MSG_CODE = 0;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");
        init();
    }

    void init() {
        listview = (ListView) findViewById(R.id.listview);
        adapter = new RestAdpater(MainActivity.this, stores);
        listview.setAdapter(adapter);
    }

    public void setListView() {

        adapter.notifyDataSetChanged();

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                dlg.setTitle("삭제확인");
                dlg.setIcon(R.drawable.store);
                dlg.setMessage("선택한 맛집을 정말 삭제할까요? ");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                count--;
                                stores.remove(position);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "선택한 맛집이 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                            }
                        });
                dlg.show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                intent.putExtra("data",stores.get(position));
                startActivity(intent);
            }
        });

    }


    public void onMyClick(View v) {

        if (v.getId() == R.id.button) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(intent, REQUEST_MSG_CODE);
        } else if (v.getId() == R.id.button2) {
            Comparator<String> dataAcs = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareToIgnoreCase(o2);
                }
            };

            Collections.sort(store, dataAcs);
            //setListView();
        } else if (v.getId() == R.id.button3) {

        } else if (v.getId() == R.id.button4) {

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_MSG_CODE) {
            if (resultCode == RESULT_OK) {
                dataset = data.getParcelableExtra("store");
                stores.add(dataset);
                store.add(dataset.getName());
                setListView();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




}
