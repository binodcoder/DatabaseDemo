package com.example.dbdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String LOGIN_TABLE="LOGIN_TABLE";
    public static final String COLUMN_USER_NAME="USER";
    public static final String COLUMN_PASSWORD="PASSWORD";
    public static final String COLUMN_ACTIVE_USER="ACTIVE_USER";
    public static final String COLUMN_ID="ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    //this is called the first time a database is accessed. There should be code in here ot create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement="CREATE TABLE "+LOGIN_TABLE+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_USER_NAME+" TEXT, "+COLUMN_PASSWORD+" INTEGER, "+COLUMN_ACTIVE_USER+" BOOL);";
        db.execSQL(createTableStatement);

    }
//this is called if the database version number changes. it prevents previous users apps from breaking when you change the database design.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(LoginModel loginModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_USER_NAME, loginModel.getUsername());
        contentValues.put(COLUMN_PASSWORD, loginModel.getPassword());
        contentValues.put(COLUMN_ACTIVE_USER, loginModel.isActive());

        long insert=db.insert(LOGIN_TABLE, null, contentValues);
        if(insert==-1){
            return false;
        }else{
            return true;
        }
    }

    public boolean deleteOne(LoginModel loginModel){
        //find LoginModel in the database. if it found, delete it and return true.
        //if it is not found, return false.
        SQLiteDatabase db=this.getWritableDatabase();
        String queryString="DELETE FROM "+LOGIN_TABLE+" WHERE "+COLUMN_ID+"="+loginModel.getId();
        Cursor cursor=db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;

        }else{
            return false;
        }

    }
    //get data from the database
    public List<LoginModel> getEveryone(){
        List<LoginModel> returnList=new ArrayList<>();
        String queryString="SELECT * FROM "+LOGIN_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            //loop through the cursor (result set) and create new customer objects. put them into the
            do{
                int loginID=cursor.getInt(0);
                String user=cursor.getString(1);
                int password=cursor.getInt(2);
                boolean active=cursor.getInt(3)==1 ? true:false;
                LoginModel newLogin=new LoginModel(loginID, user, password, active);
                returnList.add(newLogin);
            }while(cursor.moveToNext());
        }else{
            //failure. do not add anything to the list.

        }
        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return returnList;
    }
}
