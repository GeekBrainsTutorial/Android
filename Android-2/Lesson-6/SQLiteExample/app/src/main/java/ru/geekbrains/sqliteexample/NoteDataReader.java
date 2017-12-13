package ru.geekbrains.sqliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.io.Closeable;

// Читатель источника данных на основе курсора.
// Этот класс был вынесен из NoteDataSource, чтобы разгрузить его ответственности.
public class NoteDataReader implements Closeable {

    private Cursor cursor;              // Курсор, фактически это подготовенный запрос,
                                        // но сами данные подчитываются только по необходимости
    private SQLiteDatabase database;

    private String[] notesAllColumn = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_NOTE,
            DatabaseHelper.COLUMN_NOTE_TITLE
    };

    public NoteDataReader(SQLiteDatabase database){
        this.database = database;
    }

    // Подготовить к чтению таблицу
    public void open(){
        query();
        cursor.moveToFirst();
    }

    public void close(){
        cursor.close();
    }

    // Перечитать таблицу (если точно, то здесь саму таблицу мы не перечитываем,
    // а просто обновляем курсор.
    public void Refresh(){
        int position = cursor.getPosition();
        query();
        cursor.moveToPosition(position);
    }

    // создание запроса на курсор
    private void query(){
        cursor = database.query(DatabaseHelper.TABLE_NOTES,
                notesAllColumn, null, null, null, null, null);
    }

    // прочитать данные по определернной позиции
    public Note getPosition(int position){
        cursor.moveToPosition(position);
        return cursorToNote();
    }

    // получить количество строк в таблице
    public int getCount(){
        return cursor.getCount();
    }

    // преобразователь данных курсора в объект
    private Note cursorToNote() {
        Note note = new Note();
        note.setId(cursor.getLong(0));
        note.setDescription(cursor.getString(1));
        note.setTitle(cursor.getString(2));
        return note;
    }
}
