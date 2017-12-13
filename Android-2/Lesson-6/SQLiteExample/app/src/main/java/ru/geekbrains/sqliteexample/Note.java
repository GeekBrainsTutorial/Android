package ru.geekbrains.sqliteexample;

// Класс - отражение строк из таблицы
public class Note {
    private long id;
    private String description;
    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return  description;
    }

    public void setDescription(String note) {
        this.description = note;
    }

    public String getTitle() {
        return  title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
