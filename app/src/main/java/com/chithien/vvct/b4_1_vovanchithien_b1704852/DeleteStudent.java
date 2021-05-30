package com.chithien.vvct.b4_1_vovanchithien_b1704852;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DeleteStudent extends AppCompatActivity {

    EditText edt_stt;
    Button btn_return_delete, btn_delete;
    TextView show;
    DBAdapter db = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        AnhXa();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.openDB();
                int chiso = Integer.parseInt(edt_stt.getText().toString());

                if (db.deleteStudent(chiso)) {
                    show.setText("Thành công");
                }
                else {
                    show.setText("Không thành công");
                }
                db.closeDB();
            }
        });

        btn_return_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeleteStudent.this, MainActivity.class));
            }
        });
    }

    public void AnhXa(){
        edt_stt = findViewById(R.id.stt);
        btn_return_delete = findViewById(R.id.btn_return_delete);
        btn_delete = findViewById(R.id.btn_delete);
        show = findViewById(R.id.showStudent);
    }
}