package com.example.listadeitens;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final int NEW_ITEM_ID = 1;
    List<MyItem> items = new ArrayList<>();
    final List itemsSave = items;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAdapter = new MyAdapter(this,items);

        RecyclerView rvItems = (RecyclerView) findViewById(R.id.rvItem);
        rvItems.setAdapter(myAdapter);

        rvItems.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItems.setLayoutManager((layoutManager));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItems.getContext(),DividerItemDecoration.VERTICAL);
        rvItems.addItemDecoration(dividerItemDecoration);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                startActivityForResult(i,NEW_ITEM_ID);
            }
        });
    }

    //activity que retornou
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_ITEM_ID){
            if(resultCode == Activity.RESULT_OK){
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();
                items.add(myItem);
                myAdapter.notifyItemInserted(items.size()-1);//atualiza a lista
            }
        }

    }

    /* Perguntar ao professor como fazer para salvar um arrayList
    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putSerializable("myArrayList", (ArrayList<MyItem>) itemsSave);

    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);

        items = (ArrayList<MyItem>) saveInstanceState.getSerializable("myArrayList");


    }*/

}
