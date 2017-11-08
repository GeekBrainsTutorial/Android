package ru.geekbrains.textbrowser;

import android.content.Context;

// при помощи этого интерфейса развяжем активити и класс, который делает запросы
public interface Display {
    void setDisplayText(String text);
    Context getContext();
}
