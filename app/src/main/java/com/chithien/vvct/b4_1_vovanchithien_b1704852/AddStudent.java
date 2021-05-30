package com.chithien.vvct.b4_1_vovanchithien_b1704852;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddStudent extends AppCompatActivity {

    EditText edt_mssv_add, edt_ten_add, edt_email_add, edt_sdt_add;
    Button btn_return_add, btn_add;
    TextView show_SV_added;
    DBAdapter db = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        AnhXa();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.openDB();
                String mssv = edt_mssv_add.getText().toString();
                String name = edt_ten_add.getText().toString();
                String email = edt_email_add.getText().toString();
                String phone = edt_sdt_add.getText().toString();

                long id = db.insertStudent(name, mssv, email, phone);
                Cursor c = db.getAllStudent();
                c.moveToLast();
                String tx="id: " + c.getString(0) + "\n" +
                        "MSSV: " + c.getString(1) + "\n" +
                        "Name: " + c.getString(2) + "\n" +
                        "Email: " + c.getString(3) + "\n" +
                        "Phone: " + c.getString(4);
                show_SV_added.setText(tx);
                db.closeDB();
            }
        });

        btn_return_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStudent.this, MainActivity.class));
            }
        });
    }

    public void AnhXa(){
        edt_mssv_add = findViewById(R.id.edt_mssv);
        edt_ten_add = findViewById(R.id.edt_name);
        edt_email_add = findViewById(R.id.edt_email);
        edt_sdt_add = findViewById(R.id.edt_sdt);
        btn_return_add = findViewById(R.id.btn_return_add);
        btn_add = findViewById(R.id.btn_add);
        show_SV_added = findViewById(R.id.sv_added);
    }
}