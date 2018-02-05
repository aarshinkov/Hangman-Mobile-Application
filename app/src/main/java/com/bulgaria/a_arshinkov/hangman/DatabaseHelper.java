package com.bulgaria.a_arshinkov.hangman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

import static android.provider.Telephony.Carriers.PASSWORD;

/**
 * Created by a_arshinkov on 07-Dec-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hangman.db";

    SQLiteDatabase db;

    //Таблици имена
    private static final String TABLE_WORD = "Word";

    //Колони таблица
    private static final String _ID = "_id";
    private static final String NAME = "name";

    //Query за създаване на таблиците
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE " + TABLE_WORD +
                    "('" + _ID + "' INTEGER PRIMARY KEY AUTOINCREMENT,'" +
                    NAME + "' TEXT NOT NULL UNIQUE);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_WORD);
        onCreate(sqLiteDatabase);
    }

    public void addNewWord(Word word) {

        try {
            db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(NAME, word.getWord());

            db.insertOrThrow(TABLE_WORD, null, cv);
        } catch(SQLException e){
            Log.e("SQLError", e.getMessage());
        } finally {
            if(db != null) {
                db.close();
            }
        }
    }

    public Word selectWord(String name) {
        Cursor c = null;

        try{
            db = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_WORD
                    + " WHERE " + NAME + " LIKE '" + name + "'";

            c = db.rawQuery(query, null);

            if(c.moveToFirst()){
                Word word = new Word();

                word.setWord(c.getString(c.getColumnIndex(NAME)));
                return word;

            } else {
                return null;
            }


        } catch (SQLException e){
            Log.e("SQLException", e.getMessage());
        } finally {
            if(c != null)
                c.close();
            if(db != null)
                db.close();
        }
        return null;
    }
}
