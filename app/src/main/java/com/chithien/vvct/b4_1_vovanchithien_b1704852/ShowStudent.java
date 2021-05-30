package com.chithien.vvct.b4_1_vovanchithien_b1704852;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowStudent extends AppCompatActivity {
    EditText edt_stt;
    Button btn_return_show, btn_show;
    TextView show;
    DBAdapter db = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        AnhXa();

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.openDB();
                int chiso = Integer.parseInt(edt_stt.getText().toString());

                Cursor c = db.getStudent(chiso);
                //Có thêm
                if (c.moveToFirst()) {
                    String tx="id: " + c.getString(0) + "\n" +
                            "MSSV: " + c.getString(1) + "\n" +
                            "Name: " + c.getString(2) + "\n" +
                            "Email: " + c.getString(3) + "\n" +
                            "Phone: " + c.getString(4);
                    show.setText(tx);
                }
                else {
                    String tx2="No student found";
                    show.setText(tx2);
                }
                db.closeDB();
            }
        });

        btn_return_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowStudent.this, MainActivity.class));
            }
        });
    }

    public void AnhXa(){
        edt_stt = findViewById(R.id.stt);
        btn_return_show = findViewById(R.id.btn_return_show);
        btn_show = findViewById(R.id.btn_show);
        show = findViewById(R.id.showStudent);
    }
}