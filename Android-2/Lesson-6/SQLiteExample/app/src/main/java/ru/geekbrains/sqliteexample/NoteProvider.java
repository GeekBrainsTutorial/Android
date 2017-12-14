package ru.geekbrains.sqliteexample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class NoteProvider extends ContentProvider {

    static final String AUTHORITY = "ru.geekbrains.provider.SQLiteExample"; //URI authority
    static final String NOTES_PATH = "notes";   //URI path
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NOTES_PATH);
    // Типы URI для определения запроса
    static final int URI_ALL = 1;   //URI для всех записей
    static final int URI_ID = 2;    //URI для конкретрной записи
    // Типы данных
    // набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + NOTES_PATH;
    // одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + NOTES_PATH;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH, URI_ALL);
        uriMatcher.addURI(AUTHORITY, NOTES_PATH + "/#", URI_ID);
    }

    private DatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String filter = extractFilterFromUri(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NOTES, projection, filter,
                selectionArgs, null, null, sortOrder);
        // Установим нотификацию при изменении данных в CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(), CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case URI_ALL:
                return CONTACT_CONTENT_TYPE;
            case URI_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) != URI_ALL)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowID = db.insert(DatabaseHelper.TABLE_NOTES, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String filter = extractFilterFromUri(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int cnt = db.delete(DatabaseHelper.TABLE_NOTES, filter, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String filter = extractFilterFromUri(uri);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int cnt = db.update(DatabaseHelper.TABLE_NOTES, values, filter, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    private String extractFilterFromUri(Uri uri){
        switch (uriMatcher.match(uri)) {
            case URI_ALL: return "";
            case URI_ID:
                String id = uri.getLastPathSegment();
                // добавляем ID к условию выборки
                return DatabaseHelper.COLUMN_ID + " = " + id;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
    }
}
