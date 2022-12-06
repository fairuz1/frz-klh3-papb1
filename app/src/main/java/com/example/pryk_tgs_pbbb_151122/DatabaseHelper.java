package com.example.pryk_tgs_pbbb_151122;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "STUDENTS_DATA";
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String MAJOR = "major";
    public static final String GENDER = "gender";
    public static final String ADDRESS = "address";
    static final String DB_NAME = "STUDENTS.DB";
    static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + MAJOR + " TEXT NOT NULL, " + GENDER + " TEXT NOT NULL, " + ADDRESS +  " TEXT NOT NULL);";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
