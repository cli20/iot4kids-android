package com.adalaachref.iot4kids;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.adalaachref.iot4kids.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 29/12/2017.
 */

public class UserDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private dbHelper dbHelper;
    private String[] allColumns = { dbHelper.COLUMN_ID,
            dbHelper.COLUMN_Name,dbHelper.COLUMN_Email,dbHelper.COLUMN_Pwd,dbHelper.COLUMN_Id_niveau };

    public UserDataSource(Context context) {
        dbHelper = new dbHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COLUMN_ID,user.getIdUser());
        values.put(dbHelper.COLUMN_Name, user.getName());
        values.put(dbHelper.COLUMN_Email, user.getEmail());
        values.put(dbHelper.COLUMN_Pwd,user.getPassword());
        values.put(dbHelper.COLUMN_Id_niveau,user.getIdNiveau());
        long insertId = database.insert(dbHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(dbHelper.TABLE_COMMENTS,
                allColumns, dbHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public void updateUser(){

    }

    public void deleteUser(User user) {
        long id = user.getIdUser();
        System.out.println("user deleted with id: " + id);
        database.delete(dbHelper.TABLE_COMMENTS, dbHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = database.query(dbHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setIdUser(cursor.getInt(0));
        user.setName(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setEmail(cursor.getString(3));
        user.setIdNiveau(cursor.getInt(4));
        return user;
    }
}
