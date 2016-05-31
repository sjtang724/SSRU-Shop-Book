package ssru.winitawilawan.ssrushopbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pc on 31/5/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    //ประกาศตัวแปร
    public static final String database_name = "Ssru.db";
    private static final int database_version = 1;
    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "Name text," +
            "Surname text," +
            "User text," +
            "Password text," +
            "Money text);";




    public MyOpenHelper(Context context) {
        super(context,database_name,null,database_version);

    } //Constuctor


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
} //Main Class
