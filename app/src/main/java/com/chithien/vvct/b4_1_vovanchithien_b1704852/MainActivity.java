package com.chithien.vvct.b4_1_vovanchithien_b1704852;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_addSV, btn_updateSV, btn_deleteSV, btn_showSV, btn_showAllSV, btn_return;
    DBAdapter db = new DBAdapter(this);
    ListView listView;
    ImageView img;
    ArrayList<String> arrayList = null;
    ArrayAdapter<String> arrayAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        // Events
        // Show all SV
        listView = (ListView) findViewById(R.id.list_sv);
        img = (ImageView) findViewById(R.id.img_show);

        arrayList = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String> (MainActivity.this, R.layout.simple_black_item, arrayList);

        listView.setAdapter(arrayAdapter);

        btn_showAllSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.openDB();
                Cursor c = db.getAllStudent();
                if (c.moveToFirst()){
                    img.setVisibility(View.INVISIBLE);
                    do {
                        String msg = "id: " + c.getString(0) + "\n" +
                                "MSSV: " + c.getString(1) + "\n" +
                                "Name: " + c.getString(2) + "\n" +
                                "Email: " + c.getString(3) + "\n" +
                                "Phone: " + c.getString(4);
                        arrayList.add(msg);
                        arrayAdapter.notifyDataSetChanged();
                        c.moveToNext();
                    }
                    while (c.isAfterLast()==false);
                }
                db.closeDB();
            }
        });

        // Show SV
        btn_showSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowStudent.class));
            }
        });

        // Add SV
        btn_addSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddStudent.class));
            }
        });

        // Update SV
        btn_updateSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UpdateStudent.class));
            }
        });

        // Delete SV
        btn_deleteSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DeleteStudent.class));
            }
        });

        // Return home
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    public void AnhXa(){
        btn_addSV = findViewById(R.id.add_sv);
        btn_deleteSV = findViewById(R.id.delete_sv);
        btn_showAllSV = findViewById(R.id.show_all_sv);
        btn_showSV = findViewById(R.id.show_sv);
        btn_updateSV = findViewById(R.id.edit_sv);
        btn_return = findViewById(R.id.return_home);
    }
}