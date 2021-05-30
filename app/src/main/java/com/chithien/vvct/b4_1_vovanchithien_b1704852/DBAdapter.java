package com.chithien.vvct.b4_1_vovanchithien_b1704852;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    // Khai báo các cột sẽ tạo trong Database
    static final String KEY_ROWID = "_id";
    static final String KEY_MSSV = "mssv";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String KEY_PHONE = "phone";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "students";
    static final int DATABASE_VERSION = 1;
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    static final String DATABASE_CREATE = "create table students (_id integer primary key autoincrement, "
            + "mssv text not null, name text not null, email text not null, phone text not null);";
//            "CREATE TABLE "+DATABASE_TABLE+
//            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, mssv text not null, name text not null, email text not null, phone text not null);";

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    //Tạo đối tượng SQLiteOpenHelper tên là DatabaseHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            try {
                sqLiteDatabase.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            // i là old version and i1 là new version
            Log.e(TAG, "Upgrading database from version " + i + " to " + i1 + ", which will destroy all old data");

            // Xóa bảng cũ
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS students");
            // Tạo bảng mới
            onCreate(sqLiteDatabase);
        }
    }

    // Mở CSDL
    public DBAdapter openDB() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Đóng CSDL
    public void closeDB(){
        DBHelper.close();
    }

    // Thêm một nội dung vào CSDL
    public long insertStudent(String name, String mssv, String email, String phone){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_MSSV, mssv);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        return db.insert(DATABASE_TABLE, null, values);
    }

    // Xóa một nội dung của CSDL
    public boolean deleteStudent(long rowID){
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0;
    }

    // Lấy tất cả nội dung của CSDL
    public Cursor getAllStudent(){
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_MSSV, KEY_NAME,
                KEY_EMAIL, KEY_PHONE}, null, null, null, null, null);
    }

    // Lấy một nội dung của CSDL
    public Cursor getStudent(int chiso){

        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_MSSV, KEY_EMAIL, KEY_PHONE},
                KEY_ROWID +"="+ chiso, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Chỉnh sửa nội dung của CSDL
    public boolean updateStudent(long rowID, String name, String mssv, String email, String phone){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_MSSV, mssv);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        return db.update(DATABASE_TABLE, values, KEY_ROWID +"="+ rowID, null) >0;
    }
}
