package com.example.adminactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Dbmanager {

    Context context;
    SQLiteDatabase sqLiteDatabase;
    Dbhelper dbhelper;

    public Dbmanager(Context context) {
        this.context = context;
    }

    public Dbmanager open() throws SQLException{
        dbhelper=new Dbhelper(context);
        sqLiteDatabase=dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();
    }

    public Dbmanager insert(Pojo pojo){
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbhelper.STUID,pojo.getAid());
        contentValues.put(dbhelper.STUNAME,pojo.getAname());
        contentValues.put(dbhelper.STUEMAIL,pojo.getAemail());
        contentValues.put(dbhelper.STUPASS,pojo.getApass());
        sqLiteDatabase.insert(dbhelper.STUTABLE,null,contentValues);
        return this;
    }
    public Dbmanager atinsert(Pojo pojo){
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbhelper.ATANAME,pojo.getAname());
        contentValues.put(dbhelper.ATAGROUP,pojo.getGroup());

        sqLiteDatabase.insert(dbhelper.STUGROUP,null,contentValues);
        return this;
    }

    public ArrayList<Pojo>fetchall(){
        String[] columns={dbhelper.STUID,dbhelper.STUNAME,dbhelper.STUEMAIL,dbhelper.STUPASS};
        Cursor cursor=sqLiteDatabase.query(dbhelper.STUTABLE,columns,null,null,null,null,null,null);
       ArrayList arrayList=new ArrayList();
        if (cursor!=null&&cursor.moveToFirst()){
            do {
                Pojo pojo=new Pojo();
                pojo.setAid(cursor.getString(cursor.getColumnIndex(dbhelper.STUID)));
                pojo.setAname(cursor.getString(cursor.getColumnIndex(dbhelper.STUNAME)));
                pojo.setAemail(cursor.getString(cursor.getColumnIndex(dbhelper.STUEMAIL)));
                pojo.setApass(cursor.getString(cursor.getColumnIndex(dbhelper.STUPASS)));
                arrayList.add(pojo);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return arrayList;
    }

    public void delete(String id){
        sqLiteDatabase.delete(dbhelper.STUTABLE,dbhelper.STUID + " = " + id,null);
    }

    public int update(Pojo pojo){
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbhelper.STUID,pojo.getAid());
        contentValues.put(dbhelper.STUNAME,pojo.getAname());
        contentValues.put(dbhelper.STUEMAIL,pojo.getAemail());
        contentValues.put(dbhelper.STUPASS,pojo.getApass());
       int supdate= sqLiteDatabase.update(dbhelper.STUTABLE,contentValues,dbhelper.STUID + " = " + pojo.getAid(),null);
       return supdate;
    }

    public int atupdate(Pojo pojo){
        ContentValues contentValues=new ContentValues();
        contentValues.put(dbhelper.ATANAME,pojo.getAname());
        contentValues.put(dbhelper.ATAGROUP,pojo.getGroup());

        int supdate= sqLiteDatabase.update(dbhelper.ATAGROUP,contentValues,dbhelper.ATANAME + " = " + pojo.getAname(),null);
        return supdate;
    }
}
