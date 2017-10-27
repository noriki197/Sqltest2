package englishvocabulary.gameloft.com.sqltest;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 24-Oct-17.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "my_db";
    public static final String TABLE_NAME = "sinh_vien";
    public static final int DATABASE_VERSION = 2;
    public static final String CONGVIEC = "cong_viec";
    public static final String THOIGIAN = "thoi_gian";
    public static final String DIADIEM = "dia_diem";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void querydata(String sql)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getdata(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

