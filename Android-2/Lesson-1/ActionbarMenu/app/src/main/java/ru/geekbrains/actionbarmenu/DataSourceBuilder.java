package ru.geekbrains.actionbarmenu;

import android.content.res.Resources;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// построитель источника данных
public class DataSourceBuilder {
    private List<String> dataSource;   // строим этот источник данных
    private Resources resources;    // ресурсы приложения

    public DataSourceBuilder(Resources resources) {
        dataSource = new ArrayList<>(6);
        this.resources = resources;
    }

    // строим данные
    public List<String> build() {
        // Имена городов из ресурсов
        String[] descriptions = resources.getStringArray(R.array.cities);
        dataSource = Arrays.asList(descriptions);
        return dataSource;
    }
}