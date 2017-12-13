package ru.geekbrains.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Класс установки базы данных, создать базу даных, если ее нет, проапгрейдить ее
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes.db"; // название бд
    public static final int DATABASE_VERSION = 2; // версия базы данных
    static final String TABLE_NOTES = "notes"; // название таблицы в бд
    // названия столбцов
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_NOTE_TITLE = "title";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // вызывается при попытке доступа к базе данных, но когда еще эта база данных не создана
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTES + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTE + " TEXT," + COLUMN_NOTE_TITLE + " TEXT);");
    }

    // вызывается, когда необходимо обновление базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        if ((oldVersion == 1) && (newVersion == 2)) {
            String upgradeQuery = "ALTER TABLE " + TABLE_NOTES + " ADD COLUMN " + COLUMN_NOTE_TITLE + " TEXT DEFAULT Title";
            db.execSQL(upgradeQuery);
        }
    }
}
