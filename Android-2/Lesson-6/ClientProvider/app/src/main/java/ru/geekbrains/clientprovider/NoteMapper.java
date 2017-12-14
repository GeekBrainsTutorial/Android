package ru.geekbrains.clientprovider;

import android.content.ContentValues;
import android.database.Cursor;

public class NoteMapper {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTE = "note";
    public static final String COLUMN_NOTE_TITLE = "title";

    public static Note toNote(Cursor cursor){
        Note note = new Note();
        note.id = cursor.getLong(0);
        note.description = cursor.getString(1);
        note.title = cursor.getString(2);
        return note;
    }

    public static ContentValues toContent(Note note) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOTE, note.description);
        cv.put(COLUMN_NOTE_TITLE, note.title);
        return cv;
    }
}
