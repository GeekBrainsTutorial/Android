package ru.geekbrains.clientprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

public class NoteSource {

    final Uri NOTE_URI = Uri.parse("content://ru.geekbrains.provider.SQLiteExample/notes");

    private ContentResolver contentResolver;

    Cursor cursor;

    public NoteSource(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Note getNoteByPosition(int position){
        cursor.moveToPosition(position);
        return NoteMapper.toNote(cursor);
    }

    public void open(){
        cursor = query();
    }

    private Cursor query() {
        return contentResolver.query(NOTE_URI, null, null, null, null);
    }

    public int getCount(){
        return cursor.getCount();
    }

    public void insert(Note note) {
        contentResolver.insert(NOTE_URI, NoteMapper.toContent(note));
    }

    public void update(Note note) {
        Uri uri = ContentUris.withAppendedId(NOTE_URI, note.id);
        contentResolver.update(uri, NoteMapper.toContent(note), null, null);
    }

    public void delete(Note note) {
        Uri uri = ContentUris.withAppendedId(NOTE_URI, note.id);
        contentResolver.delete(uri, null, null);
    }
}
