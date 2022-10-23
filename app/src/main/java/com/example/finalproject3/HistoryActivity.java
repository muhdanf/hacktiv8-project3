package com.example.finalproject3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.finalproject3.Adapter.MyAdapter;
import com.example.finalproject3.Database.SQLiteHelper;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private SQLiteHelper database;
    private ArrayList history;
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        history = new ArrayList();

        database = new SQLiteHelper(this);

        storeDataInToArray();
        adapter = new MyAdapter(HistoryActivity.this, history);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));

    }

    private void storeDataInToArray() {
        Cursor cursor = database.readData();
        while (cursor.moveToNext()){
            history.add(cursor.getString(1));
        }
    }
}