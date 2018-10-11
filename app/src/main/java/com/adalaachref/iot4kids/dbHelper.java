package com.adalaachref.iot4kids;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dell on 17/10/2017.
 */

public class dbHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMENTS = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Name= "name";
    public static final String COLUMN_Pwd = "password";
    public static final String COLUMN_Email = "email";
    public static final String COLUMN_Id_niveau = "id_niveau";


    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_COMMENTS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_Name
            + " text not null, "+ COLUMN_Pwd +" text non null,"+COLUMN_Email+" text non null ,"+COLUMN_Id_niveau+" integer non null);";

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(dbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }
}
