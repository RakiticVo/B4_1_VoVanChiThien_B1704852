package com.chithien.vvct.b4_1_vovanchithien_b1704852;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateStudent extends AppCompatActivity {

    EditText edt_stt_update, edt_mssv_update, edt_ten_update, edt_email_update, edt_sdt_update;
    Button btn_return_update, btn_update;
    TextView show_SV_updated;
    DBAdapter db = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        AnhXa();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.openDB();
                String mssv = edt_mssv_update.getText().toString();
                String name = edt_ten_update.getText().toString();
                String email = edt_email_update.getText().toString();
                String phone = edt_sdt_update.getText().toString();

                int chiso = Integer.parseInt(edt_stt_update.getText().toString());
                if (db.updateStudent(chiso, name, mssv, email, phone)){

                    Cursor c = db.getAllStudent();
                    c.moveToPosition(chiso-1);
                    String tx="id: " + c.getString(0) + "\n" +
                            "MSSV: " + c.getString(1) + "\n" +
                            "Name: " + c.getString(2) + "\n" +
                            "Email: " + c.getString(3) + "\n" +
                            "Phone: " + c.getString(4);
                    show_SV_updated.setText(tx);
                }
                else show_SV_updated.setText("Thất bại");
                db.closeDB();
            }
        });

        btn_return_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateStudent.this, MainActivity.class));
            }
        });
    }

    public void AnhXa(){
        edt_stt_update = findViewById(R.id.edt_stt);
        edt_mssv_update = findViewById(R.id.edt_mssv_update);
        edt_ten_update = findViewById(R.id.edt_name_update);
        edt_email_update = findViewById(R.id.edt_email_update);
        edt_sdt_update = findViewById(R.id.edt_sdt_update);
        btn_return_update = findViewById(R.id.btn_return_update);
        btn_update = findViewById(R.id.btn_update);
        show_SV_updated = findViewById(R.id.sv_updated);
    }
}