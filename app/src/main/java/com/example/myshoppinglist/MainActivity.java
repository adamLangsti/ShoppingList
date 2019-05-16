package com.example.myshoppinglist;

import android.app.Dialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter mAdapter;
    private FloatingActionButton fab;
    private JSONSerializer mSerializer;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogNewItem dialog = new DialogNewItem();
                dialog.show(getSupportFragmentManager(), "");
            }
        });

        mSerializer = new JSONSerializer("ShoppingList.json",getApplicationContext());

        try {
            itemList = mSerializer.load();
        }catch (Exception e) {
            itemList = new ArrayList<Item>();
            Log.e("Error loading notes: ", "", e);
        }

        mAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(mAdapter);
    }

    public void createNewItem(Item newItem) {
        Log.i("info","NEW ITEM!!!!!" + itemList.size());
        itemList.add(newItem);
        mAdapter.notifyDataSetChanged();
    }

    public void saveItems() {
        try{
            mSerializer.save(itemList);
        }catch (Exception e) {
            Log.e("Error Saving Items", "", e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveItems();
    }
}
