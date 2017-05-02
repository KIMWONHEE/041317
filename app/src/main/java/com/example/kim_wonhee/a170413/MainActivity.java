package com.example.kim_wonhee.a170413;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    EditText search;
    Button click_Button;
    Button remove_Button;

    ArrayList<String> store = new ArrayList<String>();
    ArrayList<Data> stores = new ArrayList<Data>();
    ArrayList<Data> searchstore = new ArrayList<Data>();

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
        search = (EditText) findViewById(R.id.editText);
        click_Button = (Button) findViewById(R.id.button4);
        remove_Button = (Button) findViewById(R.id.button5);

        adapter = new RestAdpater(MainActivity.this, stores);
        listview.setAdapter(adapter);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String context = search.getText().toString();
                adapter.filter(context);
            }
        });

        remove_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove_Button.setVisibility(View.GONE);
                click_Button.setVisibility(View.VISIBLE);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                dlg.setTitle("삭제확인");
                dlg.setIcon(R.drawable.store);
                dlg.setMessage("선택한 맛집을 정말 삭제할까요? ");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = (stores.size() - 1); i >= 0; i--) {
                                    Data one = stores.get(i);
                                    if (one.getisSelected()) {
                                        stores.remove(one);
                                        adapter.notifyDataSetChanged();
                                    }
                                    one.isSelected = false;
                                }
                                Toast.makeText(getApplicationContext(), "선택한 맛집이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                adapter.setRemove();
                            }
                        });
                dlg.show();
            }

        });

    }

    public void setListView() {
        adapter.notifyDataSetChanged();

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
            adapter.setSort(adapter.NAME);
        } else if (v.getId() == R.id.button3) {
            adapter.setSort(adapter.FOOD);
        } else if (v.getId() == R.id.button4) {
            click_Button.setVisibility(View.GONE);
            remove_Button.setVisibility(View.VISIBLE);
            adapter.setCheckBox();
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

//    public void filter(String Text) {
//        Text = Text.toLowerCase();
//        searchstore.clear();
//        if (Text == "") {
//            searchstore.addAll(stores);
//        } else {
//            for (Data one : stores) {
//                String name = one.getName();
//                if (name.toLowerCase().contains(Text)) {
//                    searchstore.add(one);
//                }
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }

}
