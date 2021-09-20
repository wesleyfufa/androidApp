package me.wesleychuchuchan.motivator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "quotesData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE quotes(quote TEXT primary key )");
//        DB.execSQL("INSERT INTO quotes(quote) VALUES('This is defalt test')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE quotes");
        onCreate(DB);
    }
    public boolean insertQuotes(String quote){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quote", quote);


        long result = DB.insert("quotes",null,contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getQuotes(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM quotes ORDER BY Random() LIMIT 1", null);
//        String str = "";
//        if(cursor.moveToFirst()){
//            str = cursor.getString(cursor.getColumnIndex("quote"));
//        }
        return cursor;
    }
    public Boolean clearAllQuotes(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM quotes",null);
        if(cursor.getCount()>0){
            long result = DB.delete("quotes",null,null);
            if(result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
}
